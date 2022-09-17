package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/credentials")
public class CredentialController {

    private final UserService users;
    private final CredentialService credentials;

    public CredentialController(UserService userService, CredentialService credentialService) {
        this.users          = userService;
        this.credentials    = credentialService;
    }

    public List<String> validate(Map<String, String> data) {
        List<String> errors = new ArrayList<String>();

        if (data.get("url").isEmpty())
            errors.add("URL must not be empty.");

        if (data.get("username").isEmpty())
            errors.add("Username must not be empty.");

        if (data.get("password").isEmpty())
            errors.add("Password must not be empty.");

        return errors;
    }

    @GetMapping("{credentialId}")
    public ResponseEntity<String> decryptCredential(@PathVariable Integer credentialId, Authentication authentication) {
        try {
            Integer userId = users.getUser().getUserId();
            return new ResponseEntity<>(
                credentials.decryptCredential(new Credential(credentialId, userId)),
                HttpStatus.OK
            );

        } catch (Exception ignored) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping()
    public String createView(
        @ModelAttribute Credential credential,
        HttpServletResponse response,
        Authentication authentication,
        @RequestParam Map<String, String> data,
        Model model
    ) {
        Integer userId = users.getUser().getUserId();
        credentials.add(
            new Credential(
                credential.getCredentialId(),
                data.get("url"),
                userId,
                data.get("username"),
                data.get("password")
            )
        );

        List<String> errors = validate(data);
        model.addAttribute("success", true);
        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);

            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        return "result";
    }
    
    @GetMapping("/{credentialId}/delete")
    public String removeView(
        HttpServletResponse response,
        @PathVariable Integer credentialId,
        Authentication authentication,
        Model model
    ) {
        List<String> errors = new ArrayList<String>();
        model.addAttribute("success", true);
        try {
            Integer userId = users.getUser().getUserId();
            credentials.remove(new Credential(credentialId, userId));

        } catch (Exception ignore) {
            errors.add("There was a server error. The credential was not removed.");
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return "result";
    }


}
