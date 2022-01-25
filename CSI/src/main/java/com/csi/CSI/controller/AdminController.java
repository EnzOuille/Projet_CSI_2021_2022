package com.csi.CSI.controller;

import com.csi.CSI.objets.*;
import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.DomaineRepo;
import com.csi.CSI.repositories.VariableGlobaleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    @Autowired
    DomaineRepo domaineRepo;

    @Autowired
    AbonneRepo abnRepo;

    @Autowired
    VariableGlobaleRepo vglRepo;

    @GetMapping("/creer_domaine")
    public String getCreerDomaine(Model model, HttpServletRequest request, HttpSession session) {
        return "form_creer_domaine";
    }

    @PostMapping("/creer_domaine")
    public String postCreerDomaine(Model model, HttpServletRequest request, HttpSession session) {
        try {
            String id = session.getAttribute("abn_id").toString();
            Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(id));
            System.out.println(abonne.isAbn_admin());
            if (abonne.isAbn_admin()) {
                String nom = request.getParameter("domaine");
                Domaine domaine = new Domaine(nom,"valide");
                domaineRepo.save(domaine);
                model.addAttribute("domaine_nom", nom);
                return "result_creer_domaine";
            } else {
                String nom = request.getParameter("domaine");
                Domaine domaine = new Domaine(nom);
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

    @GetMapping("/etudier_domaine")
    public String getEtudierDomaine(Model model, HttpServletRequest request,@RequestParam long domaine_id) {
        Domaine domaine = domaineRepo.getDomaineOnlyById(domaine_id);
        model.addAttribute("dom_id", domaine.getDom_id());
        model.addAttribute("dom_nom", domaine.getDom_nom());
        model.addAttribute("dom_etat", domaine.getDom_etat());
        return "form_etudier_domaine";
    }

    @PostMapping("/etudier_domaine")
    public String postEtudierDomaine(Model model, HttpServletRequest request,@RequestParam long domaine_id) {
        Domaine domaine = domaineRepo.getDomaineOnlyById(domaine_id);
        String statut = request.getParameter("statut");
        domaine.setDom_etat(statut);
        domaineRepo.save(domaine);
        model.addAttribute("statut", statut);
        return "result_etudier_domaine";
    }

    @GetMapping("/changer_variable")
    public String getChangerVariable(Model model, HttpServletRequest request, HttpSession session) {
        try {
            String id = session.getAttribute("abn_id").toString();
            Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(id));
            VariableGlobale n = vglRepo.getVariableN();
            VariableGlobale v = vglRepo.getVariableV();
            VariableGlobale j = vglRepo.getVariableJ();
            model.addAttribute("n",n.getVgl_valeur());
            model.addAttribute("j",j.getVgl_valeur());
            model.addAttribute("v",v.getVgl_valeur());
            if (abonne.isAbn_admin()) {
                return "form_changer_variable";
            } else {
                return "home_internaute";
            }
        } catch (NullPointerException e) {
            return "home_internaute";
        } catch (Exception e) {
            return "form_changer_variable";
        }
    }

    @PostMapping("/changer_variable")
    public String postChangerVariable(Model model, HttpServletRequest request, HttpSession session) {
        try {
            String id = session.getAttribute("abn_id").toString();
            Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(id));
            if (abonne.isAbn_admin()) {
                String form_n = request.getParameter("n");
                String form_v = request.getParameter("v");
                String form_j = request.getParameter("j");
                VariableGlobale n = vglRepo.getVariableN();
                VariableGlobale v = vglRepo.getVariableV();
                VariableGlobale j = vglRepo.getVariableJ();
                n.setVgl_valeur(Long.parseLong(form_n));
                v.setVgl_valeur(Long.parseLong(form_v));
                j.setVgl_valeur(Long.parseLong(form_j));
                vglRepo.save(n);
                vglRepo.save(v);
                vglRepo.save(j);
                model.addAttribute("n",n.getVgl_valeur());
                model.addAttribute("j",j.getVgl_valeur());
                model.addAttribute("v",v.getVgl_valeur());
                return "result_changer_variable";
            } else {
                return "home_internaute";
            }
        } catch (NullPointerException e) {
            return "home_internaute";
        } catch (Exception e) {
            return "form_changer_variable";
        }
    }
}
