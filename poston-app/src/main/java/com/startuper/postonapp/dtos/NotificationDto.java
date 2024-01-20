package com.startuper.postonapp.dtos;



import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {
    private Long id;

    private String message;

    private Date dateCreated;

    private boolean isRead;

    private Long userId;

    private PostDto post;


}
