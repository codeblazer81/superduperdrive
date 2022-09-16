package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.errorprone.annotations.Var;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final FileService fileService;
    private final UserService userService;

    public HomeController (FileService fileService, UserService userService){
        this.fileService = fileService;
        this.userService = userService;
    }

    @GetMapping()
    public String homeView(Authentication auth, Model model) {
        try{

            Integer userId = userService.getUser().getUserId();

            model.addAttribute("files", fileService.allFrom(userId));

        }
        catch (Exception ignored){
            System.out.println(ignored);
            return "redirect:/logout-success";
        }
        return "home";
    }

    

 


}