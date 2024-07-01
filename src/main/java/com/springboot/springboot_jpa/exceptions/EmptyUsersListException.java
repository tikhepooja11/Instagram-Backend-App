package com.springboot.springboot_jpa.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class EmptyUsersListException extends RuntimeException {
     public EmptyUsersListException(String message) {
          super(message);
     }

}
