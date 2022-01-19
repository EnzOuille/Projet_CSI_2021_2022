package com.csi.CSI.controller;

import com.csi.CSI.objets.Abonne;
import com.csi.CSI.objets.Domaine;
import com.csi.CSI.objets.News;
import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.DomaineRepo;
import com.csi.CSI.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
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
            if (abonne.isAbn_admin()) {
                return "home_admin";
            } else if (abonne.isAbn_confiance()) {
                return "home_abonne_conf";
            } else {
                return "home_abonne";
            }
        } catch (NullPointerException e) {
            return "home_internaute";
        }
    }

    @GetMapping("/news")
    public String list_news_internaute(Model model, HttpServletRequest request, HttpSession session) {
        System.out.println("Méthode GET News");
        Object temp = session.getAttribute("abn_id");
        String abn_id = "";
        if (temp != null) {
            abn_id = temp.toString();
        }
        String typeCompte = "internaute";
        if (!abn_id.equals("")) {
            Abonne abonne = abnRepo.getAbonneById(Integer.getInteger(abn_id));
            if (abonne.isAbn_admin()) {
                typeCompte = "admin";
                model.addAttribute("type","admin");
            } else if (abonne.isAbn_confiance()) {
                typeCompte = "confiance";
                model.addAttribute("type","confiance");
            } else {
                typeCompte = "abonne";
                model.addAttribute("type","abonne");
            }
        } else {
            model.addAttribute("type","internaute");
            List<Domaine> cats = domRepo.findAllActive();
            HashMap<String, List<News>> news_list = new HashMap<>();
            for (Domaine cat : cats){
                System.out.println(cat.getDom_nom());
                List<News> last3 = newsRepo.findNewsByCategory((int) cat.getDom_id());
                news_list.put(cat.getDom_nom(),last3);
            }
            model.addAttribute("last3",news_list);
        }
        return "consulting_news";
    }
}
