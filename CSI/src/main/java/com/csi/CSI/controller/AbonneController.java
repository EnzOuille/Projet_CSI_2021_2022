package com.csi.CSI.controller;

import com.csi.CSI.objets.*;
import com.csi.CSI.repositories.*;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.Random;

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
    private DomainePrivilegieRepo domainePrivilegieRepo;

    @Autowired
    private MotCleRepo motCleRepo;

    @Autowired
    private AbonneRepo abonneRepo;

    @Autowired
    private AEvalueRepo aEvalueRepo;

    @Autowired
    private VariableGlobaleRepo varRepo;

    @GetMapping("/ajout_news")
    public String getAjoutNews(Model model, HttpServletRequest request, HttpSession session) {
        return "form_ajout_news";
    }

    @PostMapping("/ajout_news")
    public String postAjoutNews(Model model, HttpServletRequest request, HttpSession session) {
        Abonne abonne = abonneRepo.getAbonneById(Long.parseLong(session.getAttribute("abn_id").toString()));
        abonne.setAbn_nb_news(abonne.getAbn_nb_news() + 1);
        String domaine = request.getParameter("domaine");
        if (domaineRepo.getDomaineBy(domaine) == null) {
            model.addAttribute("domaine", domaine);
            return "result_domaine_inexistant";
        }
        String texte = request.getParameter("texte");
        long motClef1 = verificationKeyWord(request.getParameter("motClef1"));
        long motClef2 = verificationKeyWord(request.getParameter("motClef2"));
        long motClef3 = verificationKeyWord(request.getParameter("motClef3"));
        long abn_id = abonne.getAbn_id();
        ObjetEvalue objetEvalue = new ObjetEvalue();
        objetEvalueRepo.save(objetEvalue);
        News news = new News(objetEvalue.getObe_id(), abn_id, texte, domaineRepo.getDomaineBy(domaine).getDom_id(), motClef1, motClef2, motClef3);
        newsRepo.save(news);
        AEvalue aEvalue = new AEvalue(this.getEvaluateur(abn_id), objetEvalue.getObe_id());
        aEvalueRepo.save(aEvalue);
        abonneRepo.save(abonne);
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
        News news = newsRepo.findNewsById(new_id, (int) varRepo.getVariableJ().getVgl_valeur());
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
        News news = newsRepo.findNewsById(new_id, (int) varRepo.getVariableJ().getVgl_valeur());
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
        News news = newsRepo.findNewsById(new_id, (int) varRepo.getVariableJ().getVgl_valeur());
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
        News news = newsRepo.findNewsById(new_id, (int) varRepo.getVariableJ().getVgl_valeur());
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
        if (Objects.equals(news.getNew_etat(), "valide")) {
            abonne.setAbn_nb_news_valid(abonne.getAbn_nb_news_valid() + 1);
        }
        abonne.verifConf((int) varRepo.getVariableN().getVgl_valeur(), (int) varRepo.getVariableV().getVgl_valeur()
                , newsRepo.findNewsNotStudy((int) news.getNew_abn_id(), (int) varRepo.getVariableJ().getVgl_valeur()).size());
        abonneRepo.save(abonne);
    }

    @GetMapping("/profil")
    public String getProfil(Model model, HttpServletRequest request, HttpSession session) {
        String abonne_id = session.getAttribute("abn_id").toString();
        Abonne abonne = abonneRepo.getAbonneById(Integer.parseInt(abonne_id));
        model.addAttribute("abonne_nom", abonne.getAbn_nom());
        model.addAttribute("abonne_prenom", abonne.getAbn_prenom());
        model.addAttribute("abonne_pseudo", abonne.getAbn_pseudo());
        model.addAttribute("abonne_email", abonne.getAbn_email());
        model.addAttribute("abonne_password", abonne.getAbn_mdp());
        //model.addAttribute("abonne_url_profil", "/profil?abonne_id=" + abonne_id);

        List<DomainePrivilegie> listDomainePrivilegie = domainePrivilegieRepo.findDomainesByAbonne((int)abonne.getAbn_id());
        Domaine domaine1 = domaineRepo.getDomaineById(listDomainePrivilegie.get(0).getDpl_dom_id());
        Domaine domaine2 = domaineRepo.getDomaineById(listDomainePrivilegie.get(1).getDpl_dom_id());
        Domaine domaine3 = domaineRepo.getDomaineById(listDomainePrivilegie.get(2).getDpl_dom_id());
        model.addAttribute("domaine_1",domaine1);
        model.addAttribute("domaine_2",domaine2);
        model.addAttribute("domaine_3",domaine3);

        List<Domaine> listDomaine1 = domaineRepo.findAllActive();
        List<Domaine> listDomaine2 = domaineRepo.findAllActive();
        List<Domaine> listDomaine3 = domaineRepo.findAllActive();

        listDomaine1.remove(domaine1);
        listDomaine2.remove(domaine2);
        listDomaine3.remove(domaine3);

        model.addAttribute("domaines1",listDomaine1);
        model.addAttribute("domaines2",listDomaine2);
        model.addAttribute("domaines3",listDomaine3);
        return "form_modification_profil";
    }

    @PostMapping("/profil")
    public String postProfil(Model model, HttpServletRequest request, HttpSession session ) {
        int abonne_id = Integer.parseInt(session.getAttribute("abn_id").toString());
        Abonne abonne = abonneRepo.getAbonneById(abonne_id);
        String abonne_nom = request.getParameter("abonne_nom");
        String abonne_prenom = request.getParameter("abonne_prenom");
        String abonne_pseudo = request.getParameter("abonne_pseudo");
        String abonne_email = request.getParameter("abonne_email");
        String abonne_password = request.getParameter("abonne_password");

        abonne.setAbn_nom(abonne_nom);
        abonne.setAbn_prenom(abonne_prenom);
        abonne.setAbn_pseudo(abonne_pseudo);
        abonne.setAbn_email(abonne_email);
        abonne.setAbn_mdp(abonne_password);
        abonneRepo.save(abonne);

        List<DomainePrivilegie> listDomainePrivilegie = domainePrivilegieRepo.findDomainesByAbonne(abonne_id);

        Long abonne_domaine1 = Long.valueOf(request.getParameter("abonne_domaine_1"));
        Long abonne_domaine2 = Long.valueOf(request.getParameter("abonne_domaine_2"));
        Long abonne_domaine3 = Long.valueOf(request.getParameter("abonne_domaine_3"));

        if(listDomainePrivilegie.get(0).getDpl_dom_id() != abonne_domaine1) {
            DomainePrivilegie domaine = domainePrivilegieRepo.findDomaineByAbonneAndDomaine(abonne_id, listDomainePrivilegie.get(0).getDpl_dom_id());
            domainePrivilegieRepo.delete(domaine);
            DomainePrivilegie domaine_copie = domaine;
            domaine_copie.setDpl_dom_id(abonne_domaine1);
            domainePrivilegieRepo.save(domaine_copie);
        }
        if(listDomainePrivilegie.get(1).getDpl_dom_id() != abonne_domaine2) {
            DomainePrivilegie domaine = domainePrivilegieRepo.findDomaineByAbonneAndDomaine(abonne_id, listDomainePrivilegie.get(1).getDpl_dom_id());
            DomainePrivilegie domaine_copie = domaine;
            domainePrivilegieRepo.delete(domaine);
            domaine_copie.setDpl_dom_id(abonne_domaine2);
            domainePrivilegieRepo.save(domaine_copie);
        }
        if(listDomainePrivilegie.get(2).getDpl_dom_id() != abonne_domaine3) {
            DomainePrivilegie domaine = domainePrivilegieRepo.findDomaineByAbonneAndDomaine(abonne_id, listDomainePrivilegie.get(2).getDpl_dom_id());
            domainePrivilegieRepo.delete(domaine);
            DomainePrivilegie domaine_copie = domaine;
            domaine_copie.setDpl_dom_id(abonne_domaine3);
            domainePrivilegieRepo.save(domaine_copie);
        }
        return "redirect:/";
    }

    @GetMapping("/delete_news")
    public RedirectView deleteNews(Model model, HttpServletRequest request, @RequestParam long id) {
        News news = newsRepo.findNewsById(id);
        newsRepo.delete(news);
        AEvalue aevalue = aEvalueRepo.getAEvalueByNews(id);
        aEvalueRepo.delete(aevalue);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:9001/");
        return redirectView;
    }

    @GetMapping("/deconnexion")
    public RedirectView getDeconnexion(Model model, HttpServletRequest request, HttpSession session) {
        session.setAttribute("abn_id", null);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:9001/");
        return redirectView;
    }
}
