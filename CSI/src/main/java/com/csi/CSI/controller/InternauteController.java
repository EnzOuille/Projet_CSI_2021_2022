package com.csi.CSI.controller;

import com.csi.CSI.objets.Abonne;
import com.csi.CSI.repositories.AbonneRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class InternauteController {

    @Autowired
    private AbonneRepo abonneRepo;

    @GetMapping("/inscription")
    public String getInscription(Model model, HttpServletRequest request) {
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
        abonneRepo.save(abonne);
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
