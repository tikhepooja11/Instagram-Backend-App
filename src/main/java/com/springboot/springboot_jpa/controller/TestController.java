package com.springboot.springboot_jpa.controller;

import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot_jpa.entity.User;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class TestController {

     @GetMapping("/hello")
     public String Hello() {
          return "Welcome Pooja";
     }

}

// package com.springboot.springboot_jpa.controller;

// import java.util.List;
// import java.util.Optional;
// import javax.validation.Valid;

// import org.springframework.beans.factory.annotation.Autowired;
// import
// org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.RestController;

// import com.springboot.springboot_jpa.entity.User;
// import com.springboot.springboot_jpa.service.UserService;

// import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.PostMapping;

// @RestController
// public class UserController {

// @Autowired
// private UserService userService;

// public UserController(UserService userService) {
// this.userService = userService;
// }

// @GetMapping("/users")
// public ResponseEntity<List<User>> findAllUsers() {
// List<User> allUsersList = this.userService.findAllUsers();
// if (allUsersList.size() <= 0) {
// return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
// }
// return ResponseEntity.ok(allUsersList);
// }

// @GetMapping("/users/{id}")
// public ResponseEntity<User> findUserById(@PathVariable("id") int userId) {
// try {
// Optional<User> user = this.userService.findUserById(userId);
// if (user.isPresent()) {
// return ResponseEntity.ok(user.get());
// } else {
// return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
// }
// } catch (Exception e) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// }
// }

// @PostMapping("/users")
// public ResponseEntity<User> createNewUser(@Valid @RequestBody User newUser) {
// try {
// User createdUser = this.userService.createNewUser(newUser);
// return ResponseEntity.ok(createdUser);
// } catch (IllegalArgumentException e) {
// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
// } catch (Exception e) {
// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
// }
// }

// }
