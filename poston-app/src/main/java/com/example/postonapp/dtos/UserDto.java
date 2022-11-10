package com.example.postonapp.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {


    private Long id;
    private String username;
    private String emailAddress;
    private String password;
    private String profilePic;

}
