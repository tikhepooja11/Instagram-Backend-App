package com.springboot.springboot_jpa.exceptions;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomisedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

     @ExceptionHandler(Exception.class)
     public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
          ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                    request.getDescription(false));

          return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
     }

     @ExceptionHandler(UserNotFoundException.class)
     public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request)
               throws Exception {
          ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                    request.getDescription(false));

          return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
     }

     @ExceptionHandler(EmptyUsersListException.class)
     public final ResponseEntity<ErrorDetails> handleEmptyUsersListException(Exception ex, WebRequest request)
               throws Exception {
          ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), ex.getMessage(),
                    request.getDescription(false));
          return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NO_CONTENT);// If users is empty
     }

     @Override
     protected ResponseEntity<Object> handleMethodArgumentNotValid(
               MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

          String errorMessage = ex.getFieldError().getDefaultMessage(); // To count total no. of errors -
                                                                        // ex.getErrorCount()
          ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                    errorMessage,
                    request.getDescription(false));
          System.out.println(ex.getErrorCount());
          return new ResponseEntity(errorDetails, HttpStatus.BAD_REQUEST);// If users
     }

}
