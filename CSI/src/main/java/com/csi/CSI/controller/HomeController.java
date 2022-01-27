package com.csi.CSI.controller;

import com.csi.CSI.objets.*;
import com.csi.CSI.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    @Autowired
    private VariableGlobaleRepo varRepo;

    @GetMapping("/")
    public String list_news_internaute(Model model, HttpServletRequest request, HttpSession session) {
        int n = (int) varRepo.getVariableJ().getVgl_valeur();
        String abn_id = "";
        List<Domaine> cats = domRepo.findAllActive();
        HashMap<String, List<News>> news_list_3 = new HashMap<>();
        HashMap<String, List<News>> news_list = new HashMap<>();
        for (Domaine cat : cats) {
            List<News> last3 = newsRepo.findNewsByCategoryLast3((int) cat.getDom_id(), n);
            news_list_3.put(cat.getDom_nom(), last3);
            List<News> news = newsRepo.findNewsByCategory((int) cat.getDom_id(), n);
            news_list.put(cat.getDom_nom(), news);
        }
        model.addAttribute("news_categories", news_list);
        model.addAttribute("last3_categories", news_list_3);
        List<MotCle> keywords = keyRepo.findAll();
        HashMap<String, List<News>> news_keywords = new HashMap<>();
        for (MotCle key : keywords) {
            List<News> news_key = newsRepo.findNewsByKeyword((int) key.getMtc_id(), n);
            news_keywords.put(key.getMtc_nom(), news_key);
        }
        model.addAttribute("news_keywords", news_keywords);
        try {
            abn_id = session.getAttribute("abn_id").toString();
        } catch (Exception e) {
            abn_id = "";
        }
        if (!abn_id.equals("")) {
            model.addAttribute("abn_id",abn_id);
            List<News> news = newsRepo.findAllActiveNews(n);
            List<NewsDisplay> disp_news = new ArrayList<>();
            for (News temp_new : news) {
                Abonne abonne1 = abnRepo.getAbonneById((int) temp_new.getNew_abn_id());
                MotCle mtc1 = keyRepo.getMotCleById(temp_new.getNew_mtc_1());
                MotCle mtc2 = keyRepo.getMotCleById(temp_new.getNew_mtc_2());
                MotCle mtc3 = keyRepo.getMotCleById(temp_new.getNew_mtc_3());
                Domaine dom = domRepo.getDomaineById((int) temp_new.getNew_dom_id());
                if (dom != null) {
                    NewsDisplay newDisp = new NewsDisplay(temp_new, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), dom.getDom_nom());
                    disp_news.add(newDisp);
                }else{
                    NewsDisplay newDisp = new NewsDisplay(temp_new, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), "Domaine En Attente");
                    disp_news.add(newDisp);
                }
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
                if (dom != null) {
                    NewsDisplay newDisp = new NewsDisplay(temp_new, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), dom.getDom_nom());
                    list_archive_news.add(newDisp);
                }else{
                    NewsDisplay newDisp = new NewsDisplay(temp_new, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), "Domaine En Attente");
                    list_archive_news.add(newDisp);
                }
            }
            model.addAttribute("news_archive", list_archive_news);
            Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(abn_id));
            List<News> news_abonne = newsRepo.findNewsByAbonne(Integer.parseInt(abn_id), n);
            List<NewsDisplay> res_list = new ArrayList<>();
            for (News temp : news_abonne) {
                Abonne abonne1 = abnRepo.getAbonneById((int) temp.getNew_abn_id());
                MotCle mtc1 = keyRepo.getMotCleById(temp.getNew_mtc_1());
                MotCle mtc2 = keyRepo.getMotCleById(temp.getNew_mtc_2());
                MotCle mtc3 = keyRepo.getMotCleById(temp.getNew_mtc_3());
                Domaine dom = domRepo.getDomaineById((int) temp.getNew_dom_id());
                if (dom != null){
                    NewsDisplay newDisp = new NewsDisplay(temp, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), dom.getDom_nom());
                    res_list.add(newDisp);
                }else{
                    NewsDisplay newDisp = new NewsDisplay(temp, abonne1.getAbn_nom() + " " + abonne1.getAbn_prenom(), mtc1.getMtc_nom(), mtc2.getMtc_nom(), mtc3.getMtc_nom(), "Domaine En Attente");
                    res_list.add(newDisp);
                }
            }
            model.addAttribute("news_abonne", res_list);
            List<DomainePrivilegie> doms = domPrefRepo.findDomainesByAbonne(Integer.parseInt(abn_id));
            List<News> news_dpl = new ArrayList<>();
            for (DomainePrivilegie dpl : doms) {
                List<News> temp_news_dpl = newsRepo.findNewsByCategoryLast3((int) dpl.getDpl_dom_id(), n);
                news_dpl.addAll(temp_news_dpl);
            }
            model.addAttribute("news_dpl", news_dpl);
            model.addAttribute("type", "abonne");
            if (abonne.isAbn_admin()) {
                List<Domaine> list_dom = domRepo.getDomToStudy();
                model.addAttribute("dom_study", list_dom);
                List<News> news_to_study = newsRepo.findNewsToStudy(n);
                model.addAttribute("news_study", news_to_study);
                model.addAttribute("type", "admin");
                return "consulting_news_admin";
            } else if (abonne.isAbn_conf()) {
                List<News> news_to_study = newsRepo.findNewsToStudy(n);
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

    @Scheduled(cron = "0 0 1-7 * * *")
    public void archivageNews() {
        List<News> newsList = newsRepo.getOldNews((int) varRepo.getVariableJ().getVgl_valeur());
        newsList.forEach(news -> {
            ArchivageNews archivageNews = new ArchivageNews(news);
            archRepo.save(archivageNews);
            newsRepo.delete(news);
        });
    }
}
