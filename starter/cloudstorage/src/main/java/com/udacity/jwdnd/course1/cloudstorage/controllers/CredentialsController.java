package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CredentialsController {
    private CredentialService credentialService;
    private UserService userService;
    private final EncryptionService encryptionService;
    public CredentialsController(CredentialService credentialService, UserService userService, EncryptionService encryptionService) {
        this.credentialService = credentialService;
        this.userService = userService;
        this.encryptionService = encryptionService;
    }

    @PostMapping("/home/insertCredential")
    public String insertCredential(Credential credential, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer userId = this.userService.getUser(username).getUserId();
        credential.setUserId(userId);
        credential.setKey(encryptionService.generateKey());

        if(credential.getCredentialId() == null ){
            credentialService.insertCredential(credential);
            model.addAttribute("success", true);
        } else{
            credentialService.updateCredential(credential);
            model.addAttribute("success", true);
        }
        return "result";
    }

    @GetMapping("/home/deleteCredential/{credentialId}")
    public String deleteCredential(@PathVariable Integer credentialId) {
        credentialService.deleteCredential(credentialId);
        return "redirect:/home";
    }
}
