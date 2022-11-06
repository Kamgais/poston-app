package com.example.postonapp.mappers;

import com.example.postonapp.dtos.LikeCounterDto;
import com.example.postonapp.entities.LikeCounter;
import org.springframework.stereotype.Service;


@Service
public class LikeCounterMapper implements Mapper<LikeCounter, LikeCounterDto>{
    @Override
    public LikeCounter toEntity(LikeCounterDto likeCounterDto) {
        return null;
    }

    @Override
    public LikeCounterDto toDto(LikeCounter likeCounter) {
        return null;
    }
}
