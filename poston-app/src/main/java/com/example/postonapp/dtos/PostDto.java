package com.example.postonapp.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostDto {
    private Long id ;

    private String title;
    private String content;
    private String postImage;
    private Date dateCreated;
    private Long likeCount;
    private Long unlikeCount;
    private UserDto userDto;
    private List<CategoryDto> categories;

}
