package com.csi.CSI.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class AbonneController {

    @GetMapping("/deconnexion")
    public RedirectView getDeconnexion(Model model, HttpServletRequest request, HttpSession session) {
        session.setAttribute("abn_id",null);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:9001/");
        return redirectView;
    }
}
