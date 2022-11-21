package com.example.postonapp.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class ImageDto {
    private Long id;
    private String name;
    private String type;
    private byte[] picByte;
}
