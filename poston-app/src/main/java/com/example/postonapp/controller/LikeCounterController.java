package com.example.postonapp.controller;


import com.example.postonapp.dtos.LikeCounterDto;
import com.example.postonapp.services.LikeCounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikeCounterController {
    @Autowired
    LikeCounterService likeCounterService;
    @PutMapping("/like/{id}")
    public ResponseEntity<LikeCounterDto> likePost(@PathVariable Long id , @RequestBody Long value) {
        return likeCounterService.likePost(id, value);
    }


    @PutMapping("/unlike/{id}")
    public ResponseEntity<LikeCounterDto> unlikePost(@PathVariable Long id, @RequestBody Long value) {
        return likeCounterService.unlikePost(id,value);
    }
}
