package com.csi.CSI.controller;

import com.csi.CSI.objets.Abonne;
import com.csi.CSI.objets.Evaluateur;
import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.AdministrateurRepo;
import com.csi.CSI.repositories.EvaluateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
public class InternauteController {

    @Autowired
    private AbonneRepo abonneRepo;

    @Autowired
    private EvaluateurRepo evaluateurRepo;

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
//        System.out.println(nom);
//        System.out.println(prenom);
//        System.out.println(pseudo);
//        System.out.println(email);
        Evaluateur evaluateur = new Evaluateur();
        evaluateurRepo.save(evaluateur);
        Abonne abonne = new Abonne(evaluateur.getEvl_id(),nom, prenom, email, pseudo);
        abonneRepo.save(abonne);
        model.addAttribute("user",abonne);
        return "result_inscription";
    }
}
