package com.csi.CSI.controller;

import com.csi.CSI.repositories.AdministrateurRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
@Controller
public class InternauteController {

    @GetMapping("/inscription")
    public String new_messages(Model model, HttpServletRequest request) {
        //model.addAttribute("users");
        return "form_inscription";
    }
}
