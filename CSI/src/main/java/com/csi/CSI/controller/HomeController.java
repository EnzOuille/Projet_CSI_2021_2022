package com.csi.CSI.controller;

import com.csi.CSI.objets.Domaine;
import com.csi.CSI.objets.News;
import com.csi.CSI.objets.Abonne;
import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.DomaineRepo;
import com.csi.CSI.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private AbonneRepo abnRepo;

    @Autowired
    private DomaineRepo domRepo;

    @GetMapping("/")
    public String getHome(Model model, HttpServletRequest request, HttpSession session) {
        try {
            String id = session.getAttribute("abn_id").toString();
                Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(id));
                if (abonne.isIs_admin()) {
                    return "home_admin";
                } else if (abonne.isIs_confiance()) {
                    return "home_abonne_conf";
                } else {
                    return "home_abonne";
                }
        } catch (NullPointerException e) {
            return "home_internaute";
        }
    }

    @GetMapping("/categories")
    public String list_categories(Model model, HttpServletRequest request){
        List<Domaine> cats = domRepo.findAll();
        for(Domaine dom : cats){
            System.out.println(dom.getDom_nom());
        }
        model.addAttribute("cats", cats);
        return "list_cats";
    }

    @GetMapping("/news")
    public String list_news_internaute(@RequestParam int cat, Model model, HttpServletRequest request, HttpSession session){
        if(cat == 0){
            List<News> news = newsRepo.last3();
            model.addAttribute("fakenews",news);
        }else{
            List<News> news = newsRepo.findNewsByCategory(cat);
            model.addAttribute("fakenews",news);
        }
        return "list_news_internaute";
    }
}
