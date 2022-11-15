package com.example.postonapp.controller;


import com.example.postonapp.dtos.ImageDto;
import com.example.postonapp.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;



    @PostMapping("/upload")
    public ResponseEntity<ImageDto> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        return imageService.uploadImage(file);
    }


    @GetMapping("/{name}")
    public ResponseEntity<ImageDto> getImage(@PathVariable("name") String name) {
        return  ResponseEntity.ok().body(imageService.getImage(name));
    }
}
