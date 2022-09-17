package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping("/notes")
public class NoteController {
    private final UserService users;
    private final NoteService notes;

    public NoteController(UserService userService, NoteService noteService) {
        this.users = userService;
        this.notes = noteService;
    }

    public List<String> validate(Map<String, String> data) {
        List<String> errors = new ArrayList<String>();

        if (data.get("noteTitle").isEmpty())
            errors.add("Note title must not be empty.");

        if (data.get("noteDescription").isEmpty())
            errors.add("Note description must not be empty.");

        return errors;
    }

    @PostMapping()
    public String createUpdateView(
        @ModelAttribute Note note,
        HttpServletResponse response,
        Authentication authentication,
        @RequestParam Map<String, String> data,
        Model model
    ) {
        
        Integer userId = users.getUser().getUserId();
        
        notes.add(
            new Note(
                note.getNoteId(),
                data.get("noteTitle"),
                userId,
                data.get("noteDescription")
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


    @GetMapping(value = "/{noteId}/delete")
    public String removeView(
        HttpServletResponse response,
        @PathVariable Integer noteId,
        Authentication authentication,
        Model model
    ) {
        List<String> errors = new ArrayList<String>();
        model.addAttribute("success", true);
        try {
            Integer userId = users.getUser().getUserId();
            notes.remove(new Note(noteId, userId));

        } catch (Exception ignore) {
            errors.add("There was a server error. The note was not removed.");
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);

        }

        return "result";
    }
}
