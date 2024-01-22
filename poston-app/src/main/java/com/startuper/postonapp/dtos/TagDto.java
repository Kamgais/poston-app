package com.startuper.postonapp.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {

    private Long id;
    private PostDto post;
    private UserDto user;
}
