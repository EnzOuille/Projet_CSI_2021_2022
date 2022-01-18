package com.csi.CSI.controller;

import com.csi.CSI.objets.Abonne;
import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class HomeController {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private AbonneRepo abnRepo;

    @GetMapping("/")
    public String getHome(Model model, HttpServletRequest request, HttpSession session) {
        try {
            String id = session.getAttribute("abn_id").toString();
                Abonne abonne = abnRepo.getAbonneById(Integer.parseInt(id));
                if (abonne.isIs_admin()) {
                    return "home_admin";
                } else if (abonne.isIs_confiance()) {
                    return "home_abonne_conf";
                } else {
                    return "home_abonne";
                }
        } catch (NullPointerException e) {
            return "home_internaute";
        }
    }
}
