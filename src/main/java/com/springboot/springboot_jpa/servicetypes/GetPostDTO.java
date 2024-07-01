package com.springboot.springboot_jpa.servicetypes;

import java.util.Date;
import java.util.List;
import java.util.Set;
import lombok.*;
import com.springboot.springboot_jpa.entity.Hashtag;

@Getter
@Setter
@AllArgsConstructor
public class GetPostDTO {
     private int postId;
     private int userId;
     private String content;
     private List<String> comments;
     private Set<Hashtag> hashtags;
     private Date postedAt;
     private Date lastUpdatedAt;
     private int shares;
     private int likes;
}
