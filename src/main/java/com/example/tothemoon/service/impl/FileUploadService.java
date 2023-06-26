package com.example.tothemoon.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Component
public class FileUploadService {

    private Environment environment;
    @Autowired
    public FileUploadService(Environment environment){
        this.environment = environment;
    }

    public String[] upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();

        String[] parts = fileName.split("\\.");
        String mimeType = getFileMimeType(parts[1]);

        String[] result = new String[2];
        result[0] = mimeType;
        result[1] = "default";

        if(mimeType.equals("invalid")) {
            return result;
        }

        try {
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();

            long millis = System.currentTimeMillis();
            String generatedFilename = parts[0] + millis + "." + parts[1];

            Path path = Paths.get(environment.getProperty("file.path") + generatedFilename);
            Files.write(path, bytes);

            result[1] = generatedFilename;

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }

    public InputStreamResource getByFilename(String filename) {
        InputStreamResource resource = null;
        try {
            File file = new File(environment.getProperty("file.path") + filename);
            resource = new InputStreamResource(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resource;
    }

    public boolean delete(String filename) throws FileNotFoundException {
        File file = new File(environment.getProperty("file.path") + filename);
        if(file.delete()) {
            return true;
        }

        return false;
    }

    public String getFolderPath() {
        return environment.getProperty("file.path");
    }

    public String getFileMimeType(String extension) {
        String mimeType = "invalid";
        switch(extension) {
            case "pdf":
                mimeType = "application/pdf";
                break;
            case "zip":
                mimeType = "application/zip";
                break;
            case "png":
                mimeType = "image/png";
                break;
            case "jpg":
                mimeType = "image/jpeg";
                break;
            case "gif":
                mimeType = "image/gif";
                break;
            case "txt":
                mimeType = "text/plain";
                break;
            case "doc":
                mimeType = "application/msword";
                break;
            case "docx":
                mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
                break;
            case "odt":
                mimeType = "application/vnd.oasis.opendocument.text";
                break;
            case "xls":
                mimeType = "application/vnd.ms-excel";
                break;
            case "xlsx":
                mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
                break;
            case "ods":
                mimeType = "application/vnd.oasis.opendocument.spreadsheet";
                break;
            case "ppt":
                mimeType = "application/vnd.ms-powerpoint";
                break;
            case "pptx":
                mimeType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
                break;
            case "odp":
                mimeType = "application/vnd.oasis.opendocument.presentation";
                break;
            default:
                break;
        }

        return mimeType;
    }

}
