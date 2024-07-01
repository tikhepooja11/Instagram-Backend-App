package com.springboot.springboot_jpa.servicetypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.springboot.springboot_jpa.entity.Hashtag;

import jakarta.persistence.ElementCollection;

public class CreatePostRequest {
     private int postId;
     private String content;
     private int userId;

     private Set<Hashtag> hashtags = new HashSet<>();
     private Date postedAt;
     private Date lastUpdatedAt;

     public CreatePostRequest() {
          super();
     }

     public CreatePostRequest(int postId, String content, int userId, Set<Hashtag> hashtags) {
          this.postId = postId;
          this.content = content;
          this.userId = userId;
          this.hashtags = hashtags;
     }

     public Date getPostedAt() {
          return postedAt;
     }

     public void setPostedAt(Date postedAt) {
          this.postedAt = postedAt;
     }

     public Date getLastUpdatedAt() {
          return lastUpdatedAt;
     }

     public void setLastUpdatedAt(Date lastUpdatedAt) {
          this.lastUpdatedAt = lastUpdatedAt;
     }

     public int getPostId() {
          return postId;
     }

     public void setPostId(int postId) {
          this.postId = postId;
     }

     public String getContent() {
          return content;
     }

     public void setContent(String content) {
          this.content = content;
     }

     public int getUserId() {
          return userId;
     }

     public void setUserId(int userId) {
          this.userId = userId;
     }

     @Override
     public String toString() {
          return "CreatePostRequest [postId=" + postId + ", content=" + content + ", userId="
                    + userId + ", hashtags=" + hashtags + ", postedAt=" + postedAt + ", lastUpdatedAt=" + lastUpdatedAt
                    + "]";
     }

     public Set<Hashtag> getHashtags() {
          return hashtags;
     }

     public void setHashtags(Set<Hashtag> hashtags) {
          this.hashtags = hashtags;
     }

}
