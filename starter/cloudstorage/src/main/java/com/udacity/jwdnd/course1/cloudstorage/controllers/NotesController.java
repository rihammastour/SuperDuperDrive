package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class NotesController {
    private NoteService noteService;
    private UserService userService;
    public NotesController(NoteService noteService, UserService userService) {
        this.noteService = noteService;
        this.userService = userService;
    }

    @PostMapping("/home/insertNote")
    public String insertNote(Note note, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer userId = this.userService.getUser(username).getUserId();
        note.setUserId(userId);

        if(note.getNoteId() == null ){
            noteService.insertNote(note);
            model.addAttribute("success", true);
        } else{
            noteService.updateNote(note);
            model.addAttribute("success", true);
        }
        return "result";
    }

    @GetMapping("/home/deleteNote/{noteId}")
    public String deleteNote(@PathVariable Integer noteId) {
        noteService.deleteNote(noteId);
        return "redirect:/home";
    }
}
