package com.udacity.jwdnd.course1.cloudstorage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout-success")
public class LogoutController {
    
    @GetMapping()
    public String logoutView(Model model) {
        model.addAttribute("logoutSuccess", true);

        return "login";
    }
    
}
