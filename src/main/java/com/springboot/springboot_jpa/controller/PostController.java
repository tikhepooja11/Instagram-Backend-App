package com.springboot.springboot_jpa.controller;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot_jpa.dao.PostRepository;
import com.springboot.springboot_jpa.entity.Post;
import com.springboot.springboot_jpa.entity.Hashtag;
import com.springboot.springboot_jpa.entity.User;
import com.springboot.springboot_jpa.entity.UserActivityStats;
import com.springboot.springboot_jpa.exceptions.UserNotFoundException;
import com.springboot.springboot_jpa.service.PostService;
import com.springboot.springboot_jpa.service.UserService;
import com.springboot.springboot_jpa.servicetypes.CreatePostRequest;
import com.springboot.springboot_jpa.servicetypes.GetPostDTO;
import com.springboot.springboot_jpa.servicetypes.PostIdsRequest;
import com.springboot.springboot_jpa.servicetypes.PostsHashtagDTO;
import com.springboot.springboot_jpa.servicetypes.PostsUserDTO;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class PostController {
     @Autowired
     private PostService postService;

     @Autowired
     private UserService userService;

     public PostController(PostService postService, UserService userService) {
          this.postService = postService;
          this.userService = userService;
     }

     @PostMapping("/post")
     public Post createPost(@RequestBody CreatePostRequest newPost) {
          System.out.println(newPost);
          int userId = newPost.getUserId();
          Optional<User> user = this.userService.findUserById(userId);
          if (user.isEmpty()) {
               throw new UserNotFoundException("User with id " + userId + " not found");
          }
          User existingUser = user.get();
          return this.postService.createPost(newPost, existingUser);
     }

     @GetMapping("/post/{id}")
     public GetPostDTO findPost(@PathVariable("id") int postId) {
          GetPostDTO post = this.postService.findPost(postId);
          if (post == null) {
               throw new UserNotFoundException("Post with id " + postId + " not found");
          }
          return post;
     }

     @DeleteMapping("/post/delete/{id}")
     public void deletePost(@PathVariable("id") int postId) {
          this.postService.deletePost(postId);
     }

     @GetMapping("/post/{postId}/user")
     public PostsUserDTO findPostsUser(@PathVariable("postId") int postId) {
          return this.postService.findPostsUser(postId);
     }

     @GetMapping("/post/{postId}/hashtags")
     public Set<Hashtag> findPostsHashtags(@PathVariable("postId") int postId) {
          return this.postService.findPostsHashtags(postId);
     }

     @PostMapping("/post/hashtags")
     public List<PostsHashtagDTO> findMultiplePostsHashtags(@RequestBody PostIdsRequest postIdsRequest) {
          List<Integer> postIdsList = postIdsRequest.getPostIds();
          return this.postService.findMultiplePostsHashtags(postIdsList);
     }

}
