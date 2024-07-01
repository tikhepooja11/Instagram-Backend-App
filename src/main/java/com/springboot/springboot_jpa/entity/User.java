package com.springboot.springboot_jpa.entity;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
// @JsonFilter("dynamicUserFilter") - best practice is to use them on DTO's
// instead of entities.
public class User {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int userId;

     @Size(min = 2, message = "Name should have at least 2 characters")
     @NotBlank(message = "Name is required")
     private String name;

     @Size(max = 100, message = "City should not exceed 100 characters")
     private String city;

     @Size(max = 40, message = "Status should not exceed 40 characters")
     // @JsonProperty("profession")
     private String status;

     @Size(min = 5, message = "Password should have at least 5 characters")
     @NotBlank(message = "Password is required")
     // @JsonIgnore // static filtering - to filter properties not to display while
     // fetching data
     private String password;

     @JsonManagedReference
     @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
     private UserActivityStats userActivity;

     @JsonManagedReference
     @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
     private List<Post> posts = new ArrayList<>();

     public User() {
          super();
     }

     public User(int userId,
               String name,
               String city,
               String status,
               String password,
               UserActivityStats userActivity) {
          super();
          this.userId = userId;
          this.name = name;
          this.city = city;
          this.status = status;
          this.password = password;
          this.userActivity = userActivity;
     }

     public User(
               String name,
               String city,
               String status,
               String password,
               UserActivityStats userActivity) {
          super();
          this.name = name;
          this.city = city;
          this.status = status;
          this.password = password;
          this.userActivity = userActivity;
     }

     public void setName(String name) {
          this.name = name;
     }

     public String getName() {
          return name;
     }

     public String getCity() {
          return city;
     }

     public void setCity(String city) {
          this.city = city;
     }

     public String getStatus() {
          return status;
     }

     public void setStatus(String status) {
          this.status = status;
     }

     public String getPassword() {
          return password;
     }

     public void setPassword(String password) {
          this.password = password;
     }

     public List<Post> getPosts() {
          return posts;
     }

     public void setPosts(List<Post> posts) {
          this.posts = posts;
     }

     public UserActivityStats getUserActivity() {
          return userActivity;
     }

     public void setUserActivity(UserActivityStats userActivity) {
          this.userActivity = userActivity;
     }

     public int getUserId() {
          return userId;
     }

     public void setUserId(int userId) {
          this.userId = userId;
     }

}
