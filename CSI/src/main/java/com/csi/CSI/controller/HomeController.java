package com.csi.CSI.controller;

import com.csi.CSI.objets.*;
import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.DomaineRepo;
import com.csi.CSI.repositories.MotCleRepo;
import com.csi.CSI.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private AbonneRepo abnRepo;

    @Autowired
    private DomaineRepo domRepo;

    @Autowired
    private MotCleRepo keyRepo;

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
        Object temp = session.getAttribute("abn_id");
        String abn_id = "";
        if (temp != null) {
            abn_id = temp.toString();
        }
        if (!abn_id.equals("")) {
            Abonne abonne = abnRepo.getAbonneById(Integer.getInteger(abn_id));
            if (abonne.isAbn_admin()) {
                model.addAttribute("type", "admin");
            } else if (abonne.isAbn_confiance()) {
                model.addAttribute("type", "confiance");
            } else {
                model.addAttribute("type", "abonne");
                List<News> news = newsRepo.findAllActiveNews();
                List<NewsDisplay> disp_news = new ArrayList<>();
                for (News temp_new : news) {
                    Optional<Abonne> abonne1 = abnRepo.findById(String.valueOf(temp_new.getNew_abn_id()));
                    Optional<MotCle> mtc1 = keyRepo.findById(String.valueOf(temp_new.getNew_mtc_1()));
                    Optional<MotCle> mtc2 = keyRepo.findById(String.valueOf(temp_new.getNew_mtc_2()));
                    Optional<MotCle> mtc3 = keyRepo.findById(String.valueOf(temp_new.getNew_mtc_3()));
                    Optional<Domaine> dom = domRepo.findById(String.valueOf(temp_new.getNew_dom_id()));
                    if (abonne1.isPresent() && mtc1.isPresent() && mtc2.isPresent() && mtc3.isPresent() && dom.isPresent()) {
                        NewsDisplay newDisp = new NewsDisplay(temp_new, abonne1.get().getAbn_nom() + " " + abonne1.get().getAbn_prenom(), mtc1.get().getMtc_nom(), mtc2.get().getMtc_nom(), mtc3.get().getMtc_nom(), dom.get().getDom_nom());
                        disp_news.add(newDisp);
                    }
                }
                model.addAttribute("news", "abonne");
            }
        } else {
            model.addAttribute("type", "internaute");
            List<Domaine> cats = domRepo.findAllActive();
            HashMap<String, List<News>> news_list_3 = new HashMap<>();
            HashMap<String, List<News>> news_list = new HashMap<>();
            for (Domaine cat : cats) {
                List<News> last3 = newsRepo.findNewsByCategoryLast3((int) cat.getDom_id());
                news_list_3.put(cat.getDom_nom(), last3);
                List<News> news = newsRepo.findNewsByCategory((int) cat.getDom_id());
                news_list.put(cat.getDom_nom(), news);
            }
            model.addAttribute("news_categories", news_list);
            model.addAttribute("last3_categories", news_list_3);
            List<MotCle> keywords = keyRepo.findAll();
            HashMap<String, List<News>> news_keywords = new HashMap<>();
            for (MotCle key : keywords) {
                List<News> news_key = newsRepo.findNewsByKeyword((int) key.getMtc_id());
                news_keywords.put(key.getMtc_nom(), news_key);
            }
            model.addAttribute("news_keywords", news_keywords);
        }
        return "consulting_news";
    }
}
