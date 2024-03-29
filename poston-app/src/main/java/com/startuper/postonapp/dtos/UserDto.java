package com.startuper.postonapp.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;




@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Long id;
    private String username;
    private String emailAddress;
    private String password;
    private String profilePic;

}
