package com.springboot.springboot_jpa.servicetypes;

import java.util.List;
import lombok.*;

@Getter
@Setter
public class PostIdsRequest {
     private List<Integer> postIds;
}
