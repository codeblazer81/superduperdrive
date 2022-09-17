package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller()
@RequestMapping("/signup")
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService){
        this.userService = userService;
    }

    @GetMapping()
    public String signupView(){
        return "signup";
    }

    @PostMapping()
    public RedirectView signupUser(@ModelAttribute User user, Model model, RedirectAttributes attributes){

        String signupError = null;

        if (signupError == null){

            int rowAdded = userService.createUser(user);

            if (rowAdded < 0){
                signupError = "There was an error during Signup. Please try again.";

            }
        }

        if (signupError==null){

            RedirectView redirectView = new RedirectView("/login",true);
            attributes.addFlashAttribute("signupSuccess", true);
            //model.addAttribute("signupSuccess", true);
            return redirectView;
        }
        else {
            RedirectView redirectView = new RedirectView("/signup",true);
            attributes.addFlashAttribute("signupError", signupError);
            //model.addAttribute("signupSuccess", true);
            return redirectView;
            //model.addAttribute("signupError",signupError);
            //return "signup";
        }

        
    }


    
}
