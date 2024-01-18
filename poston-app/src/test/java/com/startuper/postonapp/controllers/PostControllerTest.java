package com.startuper.postonapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.startuper.postonapp.controller.PostController;
import com.startuper.postonapp.dtos.CategoryDto;
import com.startuper.postonapp.dtos.PostDto;
import com.startuper.postonapp.dtos.UserDto;
import com.startuper.postonapp.entities.Category;
import com.startuper.postonapp.entities.Post;
import com.startuper.postonapp.entities.User;
import com.startuper.postonapp.services.PostService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


@WebMvcTest(controllers = PostController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class PostControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Autowired
    private ObjectMapper objectMapper;

    private Post post;

    private PostDto postDto;

    private User user;

    private UserDto userDto;

    private List<Category> categories;
    private List<CategoryDto> categoriesDTO;

    @BeforeEach
    public void init() {
        user = User.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        userDto = UserDto.builder()
                .username("postonuser")
                .emailAddress("postonuser@startuper.com")
                .password("poston1!")
                .build();

        Category category1 = Category.builder()
                .categoryName("sport")
                .build();
        CategoryDto category1Dto = CategoryDto.builder()
                .categoryName("sport")
                .build();
        Category category2 = Category.builder()
                .categoryName("music")
                .build();

        CategoryDto category2Dto = CategoryDto.builder()
                .categoryName("music")
                .build();

        categoriesDTO = new ArrayList<>();
        categoriesDTO.add(category1Dto);
        categoriesDTO.add(category2Dto);


         post = Post.builder()
                .title("My Post")
                .content("My interesting Post")
                .dateCreated(new Date())
                .user(user)
                .categories(categories)
                .build();

        postDto = PostDto.builder()
                .title("My Post")
                .content("My interesting Post")
                .dateCreated(new Date())
                .userId(userDto.getId())
                .categories(categoriesDTO.stream().map(e -> e.getCategoryName()).collect(Collectors.toList()))
                .build();

    }

    @Test
    public void PostController_CreatePost_ReturnsPostDTO() throws Exception {
    given(postService.addPost(ArgumentMatchers.any())).will(invocation ->  invocation.getArgument(0));

        ResultActions response = mockMvc.perform(
                post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(postDto))
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(postDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", CoreMatchers.is(postDto.getContent())));
    }


    @Test
    public void PostController_GetAll_ReturnsMoreThanOnePost() throws Exception {
        List<PostDto> postDtoList = new ArrayList<>();
        postDtoList.add(postDto);
    when(postService.getAllPost()).thenReturn(postDtoList);

    ResultActions response = mockMvc.perform(
            get("/api/posts")
                    .contentType(MediaType.APPLICATION_JSON)
    );

    response.andExpect(MockMvcResultMatchers.status().isOk())
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.jsonPath("$.size()", CoreMatchers.is(postDtoList.size())));
    }


    @Test
    public void PostController_GetByById_ReturnsPostDTO() throws Exception {
        when(postService.getPostById(Mockito.any(Long.class))).thenReturn(postDto);

        ResultActions response = mockMvc.perform(
                get("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(postDto.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", CoreMatchers.is(postDto.getContent())));
    }


    @Test
    public void PostController_UpdatePost_ReturnsPostDTO() throws Exception {
        PostDto updatedPost = PostDto.builder()
                .title(postDto.getTitle())
                .content(post.getContent())
                .dateCreated(new Date())
                .userId(userDto.getId())
                .categories(categoriesDTO.stream().map(e -> e.getCategoryName()).collect(Collectors.toList()))
                .build();
        updatedPost.setTitle("My New Post");
        when(postService.updatePost(Mockito.anyLong(),Mockito.any(PostDto.class))).thenReturn(updatedPost);


        ResultActions response = mockMvc.perform(
                put("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedPost))

        );

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", CoreMatchers.is(updatedPost.getTitle())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content", CoreMatchers.is(updatedPost.getContent())));

    }


    @Test
    public void PostController_DeletePost_ReturnString() throws Exception {
        Mockito.doNothing().when(postService).deletePost(Mockito.anyLong());

        ResultActions response = mockMvc.perform(
                delete("/api/posts/1")
                        .contentType(MediaType.APPLICATION_JSON)
        );

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }



}
