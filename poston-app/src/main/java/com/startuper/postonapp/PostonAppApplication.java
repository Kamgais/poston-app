package com.startuper.postonapp;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.startuper.postonapp.dtos.PostDto;
import com.startuper.postonapp.entities.Category;
import com.startuper.postonapp.entities.Post;
import com.startuper.postonapp.entities.User;
import com.startuper.postonapp.services.CategoryService;
import com.startuper.postonapp.services.PostService;
import com.startuper.postonapp.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class PostonAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostonAppApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(CategoryService categoryService, UserService userService, PostService postService) {
        return args -> {
            seedCategoryData(categoryService);
            seedUserData(userService);
            seedPostData(postService);
        };
    }

    private void seedCategoryData(CategoryService service) throws IOException {
        if(service.getAllCategories().isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = PostonAppApplication.class.getResourceAsStream("/seed-data/categories.json");
             List<Category> categories = objectMapper.readValue(inputStream, new TypeReference<List<Category>>() {});
            // System.out.print(categories);
            service.saveCategories(categories);
        }
    }


    private void seedUserData(UserService service) throws IOException {
        if(service.getAllUsers().isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = PostonAppApplication.class.getResourceAsStream("/seed-data/users.json");
            List<User> users = objectMapper.readValue(inputStream, new TypeReference<List<User>>() {});
            service.saveUsers(users);

        }
    }

    private void seedPostData(PostService service) throws IOException {
        if(service.getAllPost().isEmpty()) {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = PostonAppApplication.class.getResourceAsStream("/seed-data/posts.json");
            List<PostDto> posts = objectMapper.readValue(inputStream, new TypeReference<List<PostDto>>() {});
            service.addPosts(posts);
        }
    }

}
