package com.startuper.postonapp;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.startuper.postonapp.dtos.PostDto;
import com.startuper.postonapp.entities.Category;
import com.startuper.postonapp.entities.User;
import com.startuper.postonapp.services.CategoryService;
import com.startuper.postonapp.services.PostService;
import com.startuper.postonapp.services.UserService;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class PostonAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostonAppApplication.class, args);
    }




}
