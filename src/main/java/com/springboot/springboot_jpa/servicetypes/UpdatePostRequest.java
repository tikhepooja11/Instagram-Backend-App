package com.springboot.springboot_jpa.servicetypes;

import java.util.Date;
import java.util.Set;
import lombok.*;
import com.springboot.springboot_jpa.entity.Hashtag;

@Getter
@Setter
@AllArgsConstructor
public class UpdatePostRequest {
     private String content;

     private Set<Hashtag> hashtags;

     private Date lastUpdatedAt;

     private int shares;

     private int likes;
}
