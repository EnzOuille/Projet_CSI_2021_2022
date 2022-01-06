package com.csi.CSI.controller;

import com.csi.CSI.repositories.AboneeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.ExecutionException;

@Controller
public class HomeController {

    @Autowired
    private AboneeRepository abonneeRepository;

    @GetMapping("/")
    public String new_messages(Model model, HttpServletRequest request){
        model.addAttribute("users", abonneeRepository.findAll());
        return "home";
    }


}
