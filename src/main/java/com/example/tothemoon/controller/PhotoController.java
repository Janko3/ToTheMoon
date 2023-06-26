package com.example.tothemoon.controller;

import com.example.tothemoon.service.IFileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

@Controller
@RequestMapping( "/uploads")
public class PhotoController {
    private IFileUploadService iFileUploadService;
    @Autowired
   public PhotoController(IFileUploadService iFileUploadService){
       this.iFileUploadService = iFileUploadService;
   }

    @GetMapping("/photos")
    @ResponseBody
    public byte[] getImage(@RequestParam String photoName) throws IOException {
        System.out.println(photoName);
        File file = iFileUploadService.getByFilename(photoName).getFile();
        byte[] fileContent = Files.readAllBytes(file.toPath());
        InputStream inputStream = new ByteArrayInputStream(fileContent);
        return fileContent;

    }

}

