package com.springboot.springboot_jpa.controller;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.springboot.springboot_jpa.entity.Post;
import com.springboot.springboot_jpa.entity.User;
import com.springboot.springboot_jpa.exceptions.EmptyUsersListException;
import com.springboot.springboot_jpa.exceptions.UserNotFoundException;
import com.springboot.springboot_jpa.service.UserService;
import com.springboot.springboot_jpa.servicetypes.CreateUserRequest;
import com.springboot.springboot_jpa.servicetypes.UpdateUser;
import com.springboot.springboot_jpa.utils.DynamicFilterProvider;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
public class UserController {

     @Autowired
     private UserService userService;
     private final DynamicFilterProvider filterProvider;

     public UserController(UserService userService, DynamicFilterProvider filterProvider) {
          this.userService = userService;
          this.filterProvider = filterProvider;
     }

     @GetMapping("/users")
     public ResponseEntity<List<User>> findAllUsers() {
          List<User> allUsersList = this.userService.findAllUsers();
          if (allUsersList.size() <= 0) {
               throw new EmptyUsersListException("Users list is empty...! No user exist in database.");
          }
          return ResponseEntity.ok(allUsersList);
     }

     @GetMapping("/users/{id}")
     public User findUserById(@PathVariable("id") int userId) {
          Optional<User> user = this.userService.findUserById(userId);
          if (user.isEmpty()) {
               throw new UserNotFoundException("User with id " + userId + " not found");
          }
          return user.get();
     }

     @PostMapping("/users")
     public ResponseEntity<User> createNewUser(@Valid @RequestBody CreateUserRequest newUser) {
          User createdUser = this.userService.createNewUser(newUser);
          return ResponseEntity.ok(createdUser);
     }

     @GetMapping("/users/{id}/posts")
     public List<Post> getUsersPosts(@PathVariable("id") int userId) { // one-to-many mapping check
          List<Post> posts = this.userService.getUsersPosts(userId);
          if (posts.size() <= 0) {
               throw new EmptyUsersListException("Users Post list is empty...! user has not posted anything yet.");
          }
          return posts;
     }

     @DeleteMapping("/users/{id}")
     public void deleteUser(@PathVariable("id") int userId) {
          Optional<User> user = this.userService.findUserById(userId);
          if (user.isEmpty()) {
               throw new UserNotFoundException("User with id " + userId + " not found");
          }
          this.userService.deleteUser(userId);
     }

     @PatchMapping("/users/{id}")
     public ResponseEntity<User> updateUser(@PathVariable("id") int userId,
               @Valid @RequestBody UpdateUser userDataToBeUpdated) {

          User updatedUser = this.userService.updateUser(userId, userDataToBeUpdated);
          if (updatedUser == null) {
               System.out.println("User with id " + userId + " cannot be updated");
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
          }
          return ResponseEntity.ok(updatedUser);
     }

     @GetMapping("/users/filtering")
     public MappingJacksonValue dynamicFilteringForGetAllUsers(@RequestParam(required = false) String fields) {
          String fieldsArray[] = {};
          List<User> allUsersList = this.userService.findAllUsers();
          MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(allUsersList);
          if (fields != null && !fields.isEmpty()) {
               fieldsArray = fields.split(",");
               mappingJacksonValue.setFilters(filterProvider.createFilterProvider(fieldsArray));
          }
          return mappingJacksonValue;
     }

}
