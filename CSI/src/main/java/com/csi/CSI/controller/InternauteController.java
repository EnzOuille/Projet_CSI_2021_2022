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

@Controller
public class InternauteController {

    @Autowired
    private AbonneRepo abonneRepo;

    @GetMapping("/inscription")
    public String getInscription(Model model, HttpServletRequest request) {
        return "form_inscription";
    }

    @PostMapping("/inscription")
    public String postInscription(Model model, HttpServletRequest request) {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String pseudo = request.getParameter("pseudo");
        String email = request.getParameter("email");
        String mdp = request.getParameter("password");
        Abonne abonne = new Abonne(nom, prenom, email, pseudo, mdp);
        abonneRepo.save(abonne);
        model.addAttribute("user",abonne);
        return "result_inscription";
    }

    @GetMapping("/connexion")
    public String getConnexion(Model model, HttpServletRequest request) {
        return "form_connexion";
    }

    @PostMapping("/connexion")
    public RedirectView postConnexion(Model model, HttpServletRequest request) {
        String pseudo = request.getParameter("pseudo");
        String password = request.getParameter("password");
        Abonne abonne = abonneRepo.getAbonneByLoginMdp(pseudo,password);
        String url = "http://localhost:9001/?id="+abonne.getAbn_id();
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(url);
        return redirectView;
    }
}
