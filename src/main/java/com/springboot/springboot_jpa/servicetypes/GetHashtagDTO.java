package com.springboot.springboot_jpa.servicetypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.*;

import com.springboot.springboot_jpa.entity.Post;

@Getter
@Setter
@AllArgsConstructor
public class GetHashtagDTO {
     private int hashtagId;
     private String hashtagName;
     private Set<Integer> postIds;
}
