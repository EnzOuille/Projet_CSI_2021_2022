package com.csi.CSI.controller;

import com.csi.CSI.objets.Abonne;
import com.csi.CSI.objets.Domaine;
import com.csi.CSI.objets.DomainePrivilegie;
import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.DomainePrivilegieRepo;
import com.csi.CSI.repositories.DomaineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class InternauteController {

    @Autowired
    private AbonneRepo abonneRepo;

    @Autowired
    private DomaineRepo domRepo;

    @Autowired
    private DomainePrivilegieRepo domainePrivilegieRepo;

    @GetMapping("/inscription")
    public String getInscription(Model model, HttpServletRequest request) {
        List<Domaine> list = domRepo.findAllActive();
        model.addAttribute("domaines",list);
        return "form_inscription";
    }

    @PostMapping("/inscription")
    public RedirectView postInscription(Model model, HttpServletRequest request, HttpSession session) {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        String mdp = request.getParameter("password");
        Abonne abonne = new Abonne(nom, prenom, email, pseudo, mdp);
        Abonne abo = abonneRepo.save(abonne);
        ArrayList<DomainePrivilegie> listDomToSave = new ArrayList<>();
        for (String s : List.of("domaine_1","domaine_2","domaine_3")) {
            listDomToSave.add(new DomainePrivilegie(abo.getAbn_id(),Long.parseLong(request.getParameter(s))));
        }
        domainePrivilegieRepo.saveAll(listDomToSave);

        session.setAttribute("abn_id",abonne.getAbn_id());
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:9001/");
        return redirectView;
    }

    @GetMapping("/connexion")
    public String getConnexion(Model model, HttpServletRequest request) {
        return "form_connexion";
    }

    @PostMapping("/connexion")
    public RedirectView postConnexion(Model model, HttpServletRequest request, HttpSession session) {
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");
        RedirectView redirectView = new RedirectView();
        try {
            Abonne abonne = abonneRepo.getAbonneByLoginMdp(pseudo, password);
            session.setAttribute("abn_id", abonne.getAbn_id());
            redirectView.setUrl("http://localhost:9001/");
        } catch (NullPointerException e) {
            redirectView.setUrl("http://localhost:9001/connexion");
        }
        return redirectView;
    }
}
