package com.startuper.postonapp.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

    private Long id;
    private String message;
    private UserDto user;
    private PostDto post;
}
