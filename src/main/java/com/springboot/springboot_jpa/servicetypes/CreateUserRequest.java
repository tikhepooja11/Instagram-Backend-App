package com.springboot.springboot_jpa.servicetypes;

import lombok.*;

@Getter
@Setter
public class CreateUserRequest {
     private String name;
     private String city;
     private String status;
     private String password;
}
