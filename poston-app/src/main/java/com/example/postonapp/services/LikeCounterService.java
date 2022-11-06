package com.example.postonapp.services;


import com.example.postonapp.dtos.LikeCounterDto;
import com.example.postonapp.entities.LikeCounter;
import com.example.postonapp.mappers.LikeCounterMapper;
import com.example.postonapp.repositories.ILikeCounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LikeCounterService {

    @Autowired
    ILikeCounterRepository likeCounterRepository;

    @Autowired
    LikeCounterMapper likeCounterMapper;
    public ResponseEntity<LikeCounterDto> likePost(Long id , Long value) {
        Optional<LikeCounter> likeCounter =  likeCounterRepository.findById(id);

        if(likeCounter.get() == null) {
         return ResponseEntity.badRequest().build();
        }
        likeCounter.get().setLikes(likeCounter.get().getLikes() + value);
        return ResponseEntity.ok().body(likeCounterMapper.toDto(likeCounter.get()));
    }



    public ResponseEntity<LikeCounterDto> unlikePost(Long id, Long value) {
        Optional<LikeCounter> likeCounter =  likeCounterRepository.findById(id);

        if(likeCounter.get() == null) {
            return ResponseEntity.badRequest().build();
        }
        likeCounter.get().setUnlikes(likeCounter.get().getUnlikes() + value);
        return ResponseEntity.ok().body(likeCounterMapper.toDto(likeCounter.get()));
    }
}
