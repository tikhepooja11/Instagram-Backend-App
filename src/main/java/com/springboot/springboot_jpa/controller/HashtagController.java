package com.springboot.springboot_jpa.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot_jpa.entity.Hashtag;
import com.springboot.springboot_jpa.entity.Post;
import com.springboot.springboot_jpa.entity.User;
import com.springboot.springboot_jpa.exceptions.EmptyUsersListException;
import com.springboot.springboot_jpa.exceptions.UserNotFoundException;
import com.springboot.springboot_jpa.service.HashtagService;
import com.springboot.springboot_jpa.servicetypes.GetHashtagDTO;
import com.springboot.springboot_jpa.servicetypes.GetPostDTO;
import com.springboot.springboot_jpa.servicetypes.HashtagPostsDTO;
import com.springboot.springboot_jpa.servicetypes.PostsUserDTO;

@RestController
public class HashtagController {
     @Autowired
     private HashtagService hashtagService;

     public HashtagController(HashtagService hashtagService) {
          this.hashtagService = hashtagService;
     }

     @GetMapping("/hashtag")
     public ResponseEntity<List<GetHashtagDTO>> findAllHashtags() {
          List<GetHashtagDTO> hashtagList = this.hashtagService.findAllHashtags();
          if (hashtagList.size() <= 0) {
               throw new EmptyUsersListException("Posts list is empty...! No post exist in database.");
          }
          return ResponseEntity.ok(hashtagList);
     }

     @GetMapping("/hashtag/{name}")
     public GetHashtagDTO findHashtagByName(@PathVariable("name") String hashtagName) {
          Hashtag hashtag = this.hashtagService.findHashtagByName(hashtagName);
          if (hashtag == null) {
               throw new UserNotFoundException("Hashtag with name " + hashtagName + " not found");
          }
          return mapHashtagToDTO(hashtag);
     }

     public GetHashtagDTO mapHashtagToDTO(Hashtag hashtag) {
          Set<Integer> hashtagPostIds = hashtag.getPosts().stream().map(Post::getPostId).collect(Collectors.toSet());
          return new GetHashtagDTO(hashtag.getHashtagId(), hashtag.getHashtagName(), hashtagPostIds);
     }

     @GetMapping("/hashtag/{name}/posts")
     public List<HashtagPostsDTO> findHashtagPosts(@PathVariable("name") String hashtagName) {
          Hashtag hashtag = this.hashtagService.findHashtagByName(hashtagName);
          if (hashtag == null) {
               throw new UserNotFoundException("Hashtag with id " + hashtagName + " not found");
          }
          Set<Post> hashtagPosts = this.hashtagService.findHashtagPosts(hashtagName);
          List<HashtagPostsDTO> hashtagPostsDTOList = new ArrayList<>();
          for (Post post : hashtagPosts) {
               PostsUserDTO postUser = new PostsUserDTO(post.getUser().getUserId(), post.getUser().getName(),
                         post.getUser().getCity(), post.getUser().getStatus());
               HashtagPostsDTO hashtagPostDTO = new HashtagPostsDTO(post.getPostId(), post.getContent(),
                         post.getComments(), postUser, post.getPostedAt(), post.getLastUpdatedAt(),
                         post.getShares(), post.getLikes());
               hashtagPostsDTOList.add(hashtagPostDTO);
          }
          return hashtagPostsDTOList;
     }

     @DeleteMapping("/hashtag/delete/{name}")
     public void deleteHashtagByName(@PathVariable("name") String hashtagName) {
          Hashtag hashtag = this.hashtagService.findHashtagByName(hashtagName);
          if (hashtag == null) {
               throw new UserNotFoundException("hashtag with name " + hashtagName + " not found");
          }
          this.hashtagService.deleteHashtagByName(hashtagName);
     }

     // @GetMapping("/post/{id}")
     // public GetPostDTO findPost(@PathVariable("id") int postId) {
     // GetPostDTO post = this.hashtagService.findPost(postId);
     // if (post == null) {
     // throw new UserNotFoundException("Post with id " + postId + " not found");
     // }
     // return post;
     // }

}
