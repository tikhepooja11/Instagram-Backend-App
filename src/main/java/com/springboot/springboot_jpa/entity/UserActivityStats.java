package com.springboot.springboot_jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
public class UserActivityStats {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long activityId;
     private int postsCount;
     private int likesReceived;
     private int commentsMade;

     @OneToOne()
     @JsonBackReference
     @JoinColumn(name = "user_id")
     private User user;

     public UserActivityStats() {
          super();
     }

     public UserActivityStats(int postsCount, int likesReceived, int commentsMade) {
          this.postsCount = postsCount;
          this.likesReceived = likesReceived;
          this.commentsMade = commentsMade;
     }

     @Override
     public String toString() {
          return "UserActivityStats [activityId=" + activityId + ", postsCount=" + postsCount + ", likesReceived="
                    + likesReceived + ", commentsMade=" + commentsMade + ", user=" + user + "]";
     }

}
