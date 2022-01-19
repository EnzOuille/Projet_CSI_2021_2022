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
import java.util.Random;

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
    public String getInscription(Model model, HttpServletRequest request) {
        return "form_ajout_news";
    }

    @PostMapping("/ajout_news")
    public String postInscription(Model model, HttpServletRequest request) {
        String domaine = request.getParameter("domaine");
        if (domaineRepo.getDomaineBy(domaine) == null) {
            model.addAttribute("domaine", domaine);
            return "result_domaine_inexistant";
        }
        String texte = request.getParameter("texte");
        long motClef1 = verificationKeyWord(request.getParameter("motClef1"));
        long motClef2 = verificationKeyWord(request.getParameter("motClef2"));
        long motClef3 = verificationKeyWord(request.getParameter("motClef3"));
        long abn_id = 1;
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
        if (abonneRepo.getAllTrustedAbonne(abn_id) != null) {
            List<Abonne> abonnes = abonneRepo.getAllTrustedAbonne(abn_id);
            return abonnes.get(rand.nextInt(abonnes.size())).getAbn_id();
        } else {
            List<Abonne> admins = abonneRepo.getAllAdmin();
            return admins.get(rand.nextInt(admins.size())).getAbn_id();
        }
    }
}