package com.csi.CSI.controller;

import com.csi.CSI.objets.*;
import com.csi.CSI.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private ArchivageNewsRepo archRepo;

    @Autowired
    private AbonneRepo abnRepo;

    @Autowired
    private DomaineRepo domRepo;

    @Autowired
    private DomainePrivilegieRepo domPrefRepo;

    @Autowired
    private MotCleRepo keyRepo;

    @GetMapping("/")
    public String getHome(Model model, HttpServletRequest request, HttpSession session) {
        try {
            String id = session.getAttribute("abn_id").toString();
            System.out.println("HOME " + id);
            Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(id));
            if (abonne.isAbn_admin()) {
                return "home_admin";
            } else if (abonne.isAbn_conf()) {
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
        String abn_id = "";
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
        List<News> news = newsRepo.findAllActiveNews();
        List<NewsDisplay> disp_news = new ArrayList<>();
        for (News temp_new : news) {
            Abonne abonne1 = abnRepo.getAbonneById((int) temp_new.getNew_abn_id());
            MotCle mtc1 = keyRepo.getMotCleById(temp_new.getNew_mtc_1());
            MotCle mtc2 = keyRepo.getMotCleById(temp_new.getNew_mtc_2());
            MotCle mtc3 = keyRepo.getMotCleById(temp_new.getNew_mtc_3());
            Domaine dom = domRepo.getDomaineById((int) temp_new.getNew_dom_id());
            NewsDisplay newDisp = new NewsDisplay(temp_new, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), dom.getDom_nom());
            disp_news.add(newDisp);
        }
        model.addAttribute("news", disp_news);
        List<ArchivageNews> archive_news = archRepo.findAll();
        List<NewsDisplay> list_archive_news = new ArrayList<>();
        for (ArchivageNews temp_new : archive_news) {
            Abonne abonne1 = abnRepo.getAbonneById((int) temp_new.getArc_abn_id());
            MotCle mtc1 = keyRepo.getMotCleById(temp_new.getArc_mtc_1());
            MotCle mtc2 = keyRepo.getMotCleById(temp_new.getArc_mtc_2());
            MotCle mtc3 = keyRepo.getMotCleById(temp_new.getArc_mtc_3());
            Domaine dom = domRepo.getDomaineById((int) temp_new.getArc_dom_id());
            NewsDisplay newDisp = new NewsDisplay(temp_new, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), dom.getDom_nom());
            list_archive_news.add(newDisp);
        }
        model.addAttribute("news_archive", list_archive_news);
        try {
            abn_id = session.getAttribute("abn_id").toString();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if (!abn_id.equals("")) {
            Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(abn_id));
            List<News> news_abonne = newsRepo.findNewsByAbonne(Integer.parseInt(abn_id));
            List<NewsDisplay> res_list = new ArrayList<>();
            for (News temp : news_abonne) {
                Abonne abonne1 = abnRepo.getAbonneById((int) temp.getNew_abn_id());
                MotCle mtc1 = keyRepo.getMotCleById(temp.getNew_mtc_1());
                MotCle mtc2 = keyRepo.getMotCleById(temp.getNew_mtc_2());
                MotCle mtc3 = keyRepo.getMotCleById(temp.getNew_mtc_3());
                Domaine dom = domRepo.getDomaineById((int) temp.getNew_dom_id());
                NewsDisplay newDisp = new NewsDisplay(temp, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), dom.getDom_nom());
                res_list.add(newDisp);
            }
            model.addAttribute("news_abonne", res_list);
            List<DomainePrivilegie> doms = domPrefRepo.findDomainesByAbonne(Integer.parseInt(abn_id));
            List<News> news_dpl = new ArrayList<>();
            for (DomainePrivilegie dpl : doms) {
                List<News> temp_news_dpl = newsRepo.findNewsByCategoryLast3((int) dpl.getDpl_dom_id());
                news_dpl.addAll(temp_news_dpl);
            }
            model.addAttribute("news_dpl", news_dpl);
            model.addAttribute("type", "abonne");
            if (abonne.isAbn_admin()) {
                List<Domaine> list_dom = domRepo.getDomToStudy();
                model.addAttribute("dom_study", list_dom);
                List<News> news_to_study = newsRepo.findNewsToStudy();
                model.addAttribute("news_study", news_to_study);
                model.addAttribute("type", "admin");
                return "consulting_news_admin";
            } else if (abonne.isAbn_conf()) {
                List<News> news_to_study = newsRepo.findNewsToStudy();
                model.addAttribute("news_study", news_to_study);
                model.addAttribute("type", "confiance");
                return "consulting_news_confiance";
            } else {
                return "consulting_news_abonne";
            }
        } else {
            model.addAttribute("type", "internaute");
            return "consulting_news_internaute";
        }
    }
}
