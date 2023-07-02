package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

@Controller
public class FilesController {
    private FileService fileService;
    private UserService userService;

    public FilesController(FileService fileService, UserService userService) {
        this.fileService = fileService;
        this.userService = userService;
    }

    @PostMapping("/home/uploadFile")
    public String uploadFile(@RequestParam("fileUpload") MultipartFile file, Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Integer userId = this.userService.getUser(username).getUserId();
        if(Objects.equals(file.getOriginalFilename(), "")){
            model.addAttribute("notSaved", true);
        } else if(!fileService.isSameFileName(userId, file.getOriginalFilename())){
            fileService.uploadFile(file, userId);
            model.addAttribute("success", true);
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Duplicate file name, please change your file name and ");
        }
        return "result";
    }

    @GetMapping( "/home/downloadFile/{fileId}")
    public void downloadFile(@PathVariable Integer fileId, HttpServletResponse resp) throws IOException {

        File file = fileService.getFile(fileId);

        byte[] byteArray =  file.getFileData(); // read the byteArray

        resp.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM.getType());
        resp.setHeader("Content-Disposition", "attachment; filename=" + file.getFileName());
        resp.setContentLength(byteArray.length);

        OutputStream os = resp.getOutputStream();
        try {
            os.write(byteArray, 0, byteArray.length);
        } finally {
            os.close();
        }

    }

    @GetMapping( "/home/deleteFile/{fileId}")
    public String deleteFile(@PathVariable Integer fileId) {
        fileService.deleteFile(fileId);
        return "redirect:/home";
    }
}
