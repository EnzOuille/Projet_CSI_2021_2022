package com.csi.CSI.controller;

import com.csi.CSI.objets.*;
import com.csi.CSI.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;

@Controller
public class AbonneController {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private ObjetEvalueRepo objetEvalueRepo;

    @Autowired
    private DomaineRepo domaineRepo;

    @Autowired
    private MotCleRepo motCleRepo;

    @Autowired
    private AbonneRepo abonneRepo;

    @Autowired
    private AEvalueRepo aEvalueRepo;

    @GetMapping("/ajout_news")
    public String getAjoutNews(Model model, HttpServletRequest request, HttpSession session) {
        return "form_ajout_news";
    }

    @PostMapping("/ajout_news")
    public String postAjoutNews(Model model, HttpServletRequest request, HttpSession session) {
        String domaine = request.getParameter("domaine");
        if (domaineRepo.getDomaineBy(domaine) == null) {
            model.addAttribute("domaine", domaine);
            return "result_domaine_inexistant";
        }
        String texte = request.getParameter("texte");
        long motClef1 = verificationKeyWord(request.getParameter("motClef1"));
        long motClef2 = verificationKeyWord(request.getParameter("motClef2"));
        long motClef3 = verificationKeyWord(request.getParameter("motClef3"));
        long abn_id = Long.parseLong(session.getAttribute("abn_id").toString());
        ObjetEvalue objetEvalue = new ObjetEvalue();
        objetEvalueRepo.save(objetEvalue);
        News news = new News(objetEvalue.getObe_id(), abn_id, texte, domaineRepo.getDomaineBy(domaine).getDom_id(), motClef1, motClef2, motClef3);
        newsRepo.save(news);
        AEvalue aEvalue = new AEvalue(this.getEvaluateur(abn_id), objetEvalue.getObe_id());
        aEvalueRepo.save(aEvalue);
        return "result_ajout_news";
    }

    private long verificationKeyWord(String keyWord) {
        if (motCleRepo.getMotCleBy(keyWord) == null) {
            MotCle motCle = new MotCle(keyWord);
            motCleRepo.save(motCle);
            return motCle.getMtc_id();
        } else {
            return motCleRepo.getMotCleBy(keyWord).getMtc_id();
        }
    }

    private long getEvaluateur(long abn_id) {
        Random rand = new Random();
        if (abonneRepo.getAllTrustedAbonne(abn_id) != null && abonneRepo.getAllTrustedAbonne(abn_id).size() > 0) {
            List<Abonne> abonnes = abonneRepo.getAllTrustedAbonne(abn_id);
            return abonnes.get(rand.nextInt(abonnes.size())).getAbn_id();
        } else {
            List<Abonne> admins = abonneRepo.getAllAdmin();
            return admins.get(rand.nextInt(admins.size())).getAbn_id();
        }
    }

    @GetMapping("/modification_news")
    public String getModificationNews(Model model, HttpServletRequest request, HttpSession session, @RequestParam long new_id) {
        News news = newsRepo.findNewsById(new_id);
        Domaine domaine = domaineRepo.getDomaineById(news.getNew_dom_id());
        MotCle mtc_1 = motCleRepo.getMotCleById(news.getNew_mtc_1());
        MotCle mtc_2 = motCleRepo.getMotCleById(news.getNew_mtc_2());
        MotCle mtc_3 = motCleRepo.getMotCleById(news.getNew_mtc_3());
        model.addAttribute("news", news);
        model.addAttribute("domaine", domaine);
        model.addAttribute("mtc_1", mtc_1);
        model.addAttribute("mtc_2", mtc_2);
        model.addAttribute("mtc_3", mtc_3);
        return "form_modification_news";
    }

    @PostMapping("/modification_news")
    public String postModificationNews(Model model, HttpServletRequest request, HttpSession session, @RequestParam long new_id) {
        News news = newsRepo.findNewsById(new_id);
        String domaine = request.getParameter("domaine");
        if (domaineRepo.getDomaineBy(domaine) == null) {
            model.addAttribute("domaine", domaine);
            return "result_domaine_inexistant";
        }
        String texte = request.getParameter("texte");
        long motClef1 = verificationKeyWord(request.getParameter("motClef1"));
        long motClef2 = verificationKeyWord(request.getParameter("motClef2"));
        long motClef3 = verificationKeyWord(request.getParameter("motClef3"));
        news.update(texte, domaineRepo.getDomaineBy(domaine).getDom_id(), motClef1, motClef2, motClef3);
        newsRepo.save(news);
        return "result_ajout_news";
    }

    @GetMapping("/etudier_news")
    public String getEtudierNews(Model model, HttpServletRequest request, @RequestParam long new_id) {
        News news = newsRepo.findNewsById(new_id);
        Domaine domaine = domaineRepo.getDomaineById(news.getNew_dom_id());
        MotCle mtc_1 = motCleRepo.getMotCleById(news.getNew_mtc_1());
        MotCle mtc_2 = motCleRepo.getMotCleById(news.getNew_mtc_2());
        MotCle mtc_3 = motCleRepo.getMotCleById(news.getNew_mtc_3());
        model.addAttribute("news", news);
        model.addAttribute("domaine", domaine);
        model.addAttribute("mtc_1", mtc_1);
        model.addAttribute("mtc_2", mtc_2);
        model.addAttribute("mtc_3", mtc_3);
        return "form_etudier_news";
    }

    @PostMapping("/etudier_news")
    public String postEtudierNews(Model model, HttpServletRequest request, @RequestParam long new_id) {
        News news = newsRepo.findNewsById(new_id);
        AEvalue aEvalue = aEvalueRepo.getAEvalueByNews(new_id);
        String justification = request.getParameter("justification");
        String statut = request.getParameter("statut");
        aEvalue.update(new_id, justification);
        aEvalueRepo.save(aEvalue);
        news.setNew_etat(statut);
        newsRepo.save(news);
        verificationAbnConfiance(news);
        model.addAttribute("statut", statut);
        return "result_etudier_news";
    }

    private void verificationAbnConfiance(News news) {
        Abonne abonne = abonneRepo.getAbonneById(news.getNew_abn_id());
        abonne.setAbn_nb_news(abonne.getAbn_nb_news() + 1);
        if (Objects.equals(news.getNew_etat(), "valide")) {
            abonne.setAbn_nb_news_valid(abonne.getAbn_nb_news_valid() + 1);
        }
        abonne.verifConf();
        abonneRepo.save(abonne);
    }

    @GetMapping("/deconnexion")
    public RedirectView getDeconnexion(Model model, HttpServletRequest request, HttpSession session) {
        session.setAttribute("abn_id", null);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:9001/");
        return redirectView;
    }
}
