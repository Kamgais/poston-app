package com.example.postonapp.mappers;

import com.example.postonapp.dtos.ImageDto;
import com.example.postonapp.entities.Image;
import org.springframework.stereotype.Service;


@Service
public class ImageMapper implements Mapper<Image, ImageDto>{

    @Override
    public Image toEntity(ImageDto imageDto) {
        if(imageDto == null) {
            return null;
        } else {
            return Image.builder()
                    .name(imageDto.getName())
                    .type(imageDto.getType())
                    .picByte(imageDto.getPicByte())
                    .build();
        }

    }

    @Override
    public ImageDto toDto(Image image) {
        if(image == null) {
            return null;
        } else {
           return  ImageDto.builder()
                    .id(image.getId())
                    .name(image.getName())
                    .type(image.getType())
                    .picByte(image.getPicByte())
                    .build();
        }

    }
}
