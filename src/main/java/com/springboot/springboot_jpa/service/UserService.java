package com.springboot.springboot_jpa.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.springboot.springboot_jpa.dao.UserRepository;
import com.springboot.springboot_jpa.entity.Post;
import com.springboot.springboot_jpa.entity.User;
import com.springboot.springboot_jpa.entity.UserActivityStats;
import com.springboot.springboot_jpa.servicetypes.CreateUserRequest;
import com.springboot.springboot_jpa.servicetypes.UpdateUser;

@Service
public class UserService {

     @Autowired
     private UserRepository userRepository;

     public UserService(UserRepository userRepository) {
          this.userRepository = userRepository;
     }

     public List<User> findAllUsers() {
          Iterable<User> allUsersIterable = this.userRepository.findAll();
          List<User> allUsersList = new ArrayList<User>();
          allUsersIterable.forEach(user -> allUsersList.add(user));
          return allUsersList;

     }

     public Optional<User> findUserById(int userId) {
          Optional<User> optionalUser = this.userRepository.findById(userId);
          return optionalUser;
     }

     public List<Post> getUsersPosts(int userId) {
          List<Post> usersPosts = this.userRepository.getUserPosts(userId);
          return usersPosts;
     }

     public User createNewUser(CreateUserRequest newUser) {
          UserActivityStats userActivityStats = new UserActivityStats(0, 0, 0);
          User createNewUser = new User(newUser.getName(), newUser.getCity(), newUser.getStatus(),
                    newUser.getPassword(), userActivityStats);
          // Set the relationship both ways
          userActivityStats.setUser(createNewUser);
          createNewUser.setUserActivity(userActivityStats);
          User user = this.userRepository.save(createNewUser);
          return user;
     }

     public void deleteUser(int userId) {
          this.userRepository.deleteById(userId);
     }

     public User updateUser(int userId, UpdateUser userDataToBeUpdated) {
          Optional<User> optionalUser = this.userRepository.findById(userId);
          if (optionalUser.isPresent()) {
               User existingUser = optionalUser.get();
               if (userDataToBeUpdated.name != null) {
                    existingUser.setName(userDataToBeUpdated.name);
               }
               if (userDataToBeUpdated.city != null) {
                    existingUser.setCity(userDataToBeUpdated.city);
               }
               if (userDataToBeUpdated.status != null) {
                    existingUser.setName(userDataToBeUpdated.status);
               }
               if (userDataToBeUpdated.password != null) {
                    existingUser.setPassword(userDataToBeUpdated.password);
               }
               User updatedUser = userRepository.save(existingUser);
               return updatedUser;
          }
          return null;
     }
}
