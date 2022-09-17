package com.udacity.jwdnd.course1.cloudstorage.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.MediaType;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;

@Controller
@RequestMapping ("/files")
public class FileController {

    private final FileService fileService;
    private final UserService userService;


    public FileController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }
    
    @PostMapping()
   
    
	public String handleFileUpload(@RequestParam ("fileUpload") MultipartFile file,
			Model model, HttpServletResponse response) {
            
            List <String> errors = new ArrayList<String>();
            try {
           

            if (file.isEmpty()){
                errors.add("File field must not be empty");
            }

            model.addAttribute("success", true);

            fileService.uploadFile(file);
        } catch (Exception e) {
            errors.add("Something went wrong.");
            model.addAttribute("success", false);
            model.addAttribute("errors", errors);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            
        }

        if (!errors.isEmpty()){
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
        

		return "result";
	}
    @GetMapping(value = "/{fileId}")
    public ResponseEntity<ByteArrayResource> downloadView(
        HttpServletResponse response,
        Authentication authentication,
        @PathVariable Integer fileId
    ) {
        Integer userId = userService.getUser().getUserId();
        File file   = fileService.getFile(new File(fileId, userId));

        if (file != null) {
            return ResponseEntity.ok()
                //.contentType(MediaType.parse(file.getContentType()))
                .header("Content-Disposition", "attachment; filename=" + file.getFileName())
                .body(new ByteArrayResource(file.getFileData()));
        }

        return ResponseEntity.notFound().build();
    }

    @GetMapping(value = "/{fileId}/delete")
    public String removeView(
        Authentication authentication,
        HttpServletResponse response,
        @PathVariable Integer fileId,
        Model model
    ) {
        Integer userId = userService.getUser().getUserId();
        

        List<String> errors = new ArrayList<String>();
        model.addAttribute("success", true);
        try {
            fileService.removeFile(new File(fileId, userId));

        } catch (Exception ignore) {
            errors.add("There was a server error. The file was not removed.");
            model.addAttribute("errors", errors);
            model.addAttribute("success", false);
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        return "result";
    }
    
}
