package com.example.tothemoon.service;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

@Service
public interface IFileUploadService {
    public String[] upload( MultipartFile multipartFile);
    public InputStreamResource getByFilename(String filename);
    public boolean delete(String filename) throws FileNotFoundException;
    public String getFolderPath();
    public String getFileMimeType(String extension);

}
