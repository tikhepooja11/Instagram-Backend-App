package com.springboot.springboot_jpa.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hashtag {
     @Id
     @GeneratedValue(strategy = GenerationType.AUTO)
     private int hashtagId;
     private String hashtagName;

     // @JsonBackReference ////
     @JsonIgnore // this worked instead of managed and backref
     @ManyToMany(fetch = FetchType.EAGER, mappedBy = "hashtags")
     private Set<Post> posts = new HashSet<>();

     @Override
     public String toString() {
          return "Hashtag [hashtagId=" + hashtagId + ", hashtagName=" + hashtagName + "]";
     }

}
