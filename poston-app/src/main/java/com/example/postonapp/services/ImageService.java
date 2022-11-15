package com.example.postonapp.services;


import com.example.postonapp.dtos.ImageDto;
import com.example.postonapp.entities.Image;
import com.example.postonapp.mappers.ImageMapper;
import com.example.postonapp.repositories.IImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
public class ImageService {

    @Autowired
    private IImageRepository imageRepository;

    @Autowired
    private ImageMapper imageMapper;

    public ResponseEntity<ImageDto> uploadImage(MultipartFile file) throws IOException {

            ImageDto imageDto = ImageDto.builder()
                    .name(file.getOriginalFilename())
                    .type(file.getContentType())
                    .picByte(ImageService.compressBytes(file.getBytes()))
                    .build();
           Image image =  imageRepository.save(imageMapper.toEntity(imageDto));

           return ResponseEntity.ok().body(imageMapper.toDto(image));
    }

    public ImageDto getImage(String imageName) {

        Image image = imageRepository.findImageByName(imageName);

        Image responseImage = Image.builder()
                .id(image.getId())
                .name(image.getName())
                .type(image.getType())
                .picByte(ImageService.decompressBytes(image.getPicByte()))
                .build();

        return imageMapper.toDto(responseImage);

    }




    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        while(!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }

        try{
            outputStream.close();
        } catch(IOException e) {

        }

        return outputStream.toByteArray();
    }



    // uncompress the image bytes before returning it to the react application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];

        try{
            while(!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0 , count);
            }

            outputStream.close();
        }  catch (IOException | DataFormatException e) {

        }
        return outputStream.toByteArray();
    }
}
