package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class FileService {
    private final FilesMapper filesMapper;
    public FileService(FilesMapper filesMapper) {
        this.filesMapper = filesMapper;
    }

    public File getFile(Integer fileId){
        return this.filesMapper.getFile(fileId);
    }

    public List<File> getAllFiles(Integer userId){
        return this.filesMapper.getAllFiles(userId);
    }

    public void uploadFile(MultipartFile multipartFile, Integer userId){
        try {
            File file = new File();
            file.setFileName(multipartFile.getOriginalFilename());
            file.setFileSize(String.valueOf(multipartFile.getSize()));
            file.setFileData(multipartFile.getBytes());
            file.setContentType(multipartFile.getContentType());
            file.setUserId(userId);
            this.filesMapper.insertFile(file);
        } catch (IOException ioException){
            ioException.printStackTrace();

        }

    }

    public void deleteFile(Integer fileId){
        this.filesMapper.deleteFile(fileId);
    }

    public boolean isSameFileName(Integer userId, String fileName) {
         return this.filesMapper.getAllFiles(userId).stream()
                .filter(file -> fileName.equals(file.getFileName()))
                .findAny()
                .orElse(null) != null;
    }

}
