package com.csi.CSI.controller;

import com.csi.CSI.repositories.AdministrateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @Autowired
    private AdministrateurRepo adminRepo;

    @GetMapping("/")
    public String home(@RequestParam(name="id", required = false) Model model, HttpServletRequest request) {
//        model.addAttribute("users", adminRepo.findAll());

        return "home";
    }

}
