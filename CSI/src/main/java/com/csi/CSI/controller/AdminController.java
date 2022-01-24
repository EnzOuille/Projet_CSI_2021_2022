package com.csi.CSI.controller;

import com.csi.CSI.objets.Abonne;
import com.csi.CSI.objets.Domaine;
import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.DomaineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    DomaineRepo domaineRepo;

    @Autowired
    AbonneRepo abnRepo;

    @GetMapping("/creer_domaine")
    public String getCreerDomaine(Model model, HttpServletRequest request, HttpSession session) {
        return "form_creer_domaine";
    }

    @PostMapping("/creer_domaine")
    public String postCreerDomaine(Model model, HttpServletRequest request, HttpSession session) {
        try {
            String id = session.getAttribute("abn_id").toString();
            Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(id));
            if (abonne.isAbn_admin()) {
                String nom = request.getParameter("domaine");
                Domaine domaine = new Domaine(nom);
                domaineRepo.save(domaine);
                model.addAttribute("domaine_nom", nom);
                return "result_creer_domaine";
            } else {
                String nom = request.getParameter("domaine");
                Domaine domaine = new Domaine(nom,"en attente");
                domaineRepo.save(domaine);
                model.addAttribute("domaine_nom", nom);
                return "result_creer_domaine";
            }
        } catch (NullPointerException e) {
            return "home_internaute";
        } catch (Exception e) {
            return "form_creer_domaine";
        }
    }
}
