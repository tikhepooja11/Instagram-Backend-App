package com.springboot.springboot_jpa.servicetypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.*;

import jakarta.persistence.ElementCollection;

@Getter
@Setter
@AllArgsConstructor
public class HashtagPostsDTO {
     private int postId;
     private String content;
     private List<String> comments;
     private PostsUserDTO user;
     private Date postedAt;
     private Date lastUpdatedAt;
     private int shares;
     private int likes;

}
