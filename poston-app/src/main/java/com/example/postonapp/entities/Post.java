package com.example.postonapp.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name= "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String title;
    private String content;
    private Date dateCreated;
    private Long likeCount;
    private Long unlikeCount;


    @ManyToMany(fetch = EAGER)
    private List<Category> categories;

    @ManyToOne
    private User user;


    @OneToOne(cascade = CascadeType.ALL)
    private Image image;




}
