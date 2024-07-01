package com.springboot.springboot_jpa.servicetypes;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateUser {

     @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
     public String name;

     @Size(max = 100, message = "City should not exceed 100 characters")
     public String city;

     @Size(max = 40, message = "Status should not exceed 40 characters")
     public String status;

     @Size(min = 5, message = "Password should have at least 5 characters")
     public String password;

}
