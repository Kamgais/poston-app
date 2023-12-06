package com.startuper.postonapp.controller;


import com.startuper.postonapp.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/storage")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(HttpStatus.CREATED).body(storageService.uploadFile(file));
    }
}
