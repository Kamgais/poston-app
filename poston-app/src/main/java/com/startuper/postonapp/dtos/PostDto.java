package com.startuper.postonapp.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;


import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {
    private Long id ;

    private String title;
    private String content;
    private Date dateCreated;
    private Long likeCount;
    private Long unlikeCount;
    private Long userId;
    private String postImage;
    private List<String> categories;

}
