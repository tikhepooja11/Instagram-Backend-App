package com.springboot.springboot_jpa.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.util.Date;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class Post {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int postId;

     private String content;

     @ElementCollection
     private List<String> comments = new ArrayList<>();

     @ManyToOne(fetch = FetchType.LAZY)
     @JoinColumn(name = "user_id", referencedColumnName = "userId")
     @JsonBackReference
     private User user;

     @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     @JoinTable(name = "post_hashtags", joinColumns = {
               @JoinColumn(name = "post_id", referencedColumnName = "postId") }, inverseJoinColumns = {
                         @JoinColumn(name = "hashtag_id", referencedColumnName = "hashtagId") }

     )
     // @JsonManagedReference
     private Set<Hashtag> hashtags = new HashSet<>();

     @Temporal(TemporalType.TIMESTAMP)
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
     private Date postedAt = new Date();

     @Temporal(TemporalType.TIMESTAMP)
     @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
     private Date lastUpdatedAt = new Date();

     private int shares;

     private int likes;

     public Post() {
          super();
     }

     public Post(int postId, String content, User user, Set<Hashtag> hashtags) {
          this.postId = postId;
          this.content = content;
          this.user = user;
          this.hashtags = hashtags;
     }

     public int getShares() {
          return shares;
     }

     public void setShares(int shares) {
          this.shares = shares;
     }

     public int getLikes() {
          return likes;
     }

     public void setLikes(int likes) {
          this.likes = likes;
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

     public User getUser() {
          return user;
     }

     public void setUser(User user) {
          this.user = user;
     }

     public String getContent() {
          return content;
     }

     public void setContent(String content) {
          this.content = content;
     }

     public List<String> getComments() {
          return comments;
     }

     public void setComments(List<String> comments) {
          this.comments = comments;
     }

     public int getPostId() {
          return postId;
     }

     public void setPostId(int postId) {
          this.postId = postId;
     }

     @Override
     public String toString() {
          return "Post [postId=" + postId + ", content=" + content + ", comments=" + comments + ", user=" + user
                    + ", hashtags=" + hashtags + ", postedAt=" + postedAt + ", lastUpdatedAt=" + lastUpdatedAt
                    + ", shares=" + shares + ", likes=" + likes + "]";
     }

     public Set<Hashtag> getHashtags() {
          return hashtags;
     }

     public void setHashtags(Set<Hashtag> hashtags) {
          this.hashtags = hashtags;
     }

     // private List<String> mentioned_people;

     // private int shares;

     // private int likes;

}
