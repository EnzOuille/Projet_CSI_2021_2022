package com.csi.CSI.controller;

import com.csi.CSI.repositories.AbonneRepo;
import com.csi.CSI.repositories.NewsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private NewsRepo newsRepo;

    @Autowired
    private AbonneRepo abnRepo;

    @GetMapping("/")
    public String new_messages(Model model, HttpServletRequest request) {
        model.addAttribute("users", abnRepo.findAll());
        return "home";
    }
}
