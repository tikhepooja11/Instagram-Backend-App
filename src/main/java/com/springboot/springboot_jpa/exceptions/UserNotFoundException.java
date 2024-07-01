package com.springboot.springboot_jpa.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class UserNotFoundException extends RuntimeException {
     public UserNotFoundException(String message) {
          super(message);
     }
}
