package com.springboot.springboot_jpa.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springboot_jpa.dao.HashtagRepository;
import com.springboot.springboot_jpa.dao.PostRepository;
import com.springboot.springboot_jpa.entity.Hashtag;
import com.springboot.springboot_jpa.entity.Post;
import com.springboot.springboot_jpa.servicetypes.GetHashtagDTO;
import com.springboot.springboot_jpa.servicetypes.GetPostDTO;

@Service
public class HashtagService {
     @Autowired
     private HashtagRepository hashtagRepository;

     @Autowired
     private PostRepository postRepository;

     public HashtagService(HashtagRepository hashtagRepository) {
          this.hashtagRepository = hashtagRepository;
     }

     public List<GetHashtagDTO> findAllHashtags() {
          Iterable<Hashtag> allIterableHashtags = this.hashtagRepository.findAll();
          List<GetHashtagDTO> hashtagDTOList = new ArrayList<>();
          for (Hashtag hashtag : allIterableHashtags) {
               GetHashtagDTO hashtagDTO = mapHashtagToDTO(hashtag);
               hashtagDTOList.add(hashtagDTO);
          }
          return hashtagDTOList;
     }

     public Hashtag findHashtagByName(String hashtagName) {
          Hashtag hashtag = this.hashtagRepository.findHashtagByName(hashtagName);
          return hashtag;
     }

     public Optional<Hashtag> findHashtagById(int hashtagId) {
          Optional<Hashtag> hashtag = this.hashtagRepository.findById(hashtagId);
          return hashtag;
     }

     public GetHashtagDTO mapHashtagToDTO(Hashtag hashtag) {
          Set<Integer> hashtagPostIds = hashtag.getPosts().stream().map(Post::getPostId).collect(Collectors.toSet());
          return new GetHashtagDTO(hashtag.getHashtagId(), hashtag.getHashtagName(), hashtagPostIds);
     }

     public Set<Hashtag> updateHashtagsOnNewPostCreation(Post post, Set<Hashtag> hashtags) {
          Set<Hashtag> hashtagsToAssociate = new HashSet<>();
          for (Hashtag hashtag : hashtags) {
               Hashtag existingHashtag = this.findHashtagByName(hashtag.getHashtagName());
               if (existingHashtag != null) {
                    existingHashtag.getPosts().add(post);
                    hashtagsToAssociate.add(existingHashtag);
               } else {
                    Hashtag newHashtag = new Hashtag();
                    newHashtag.setHashtagName(hashtag.getHashtagName());
                    newHashtag.getPosts().add(post);
                    hashtagsToAssociate.add(newHashtag);
               }
          }
          return hashtagsToAssociate;
     }

     public Set<Hashtag> updateHashtags(Post post, Set<Hashtag> updateReqHashtags) {
          Set<Hashtag> updatedHashtags = new HashSet<>();

          // Case 1: User wants to delete all hashtags
          if (updateReqHashtags.isEmpty()) {
               post.getHashtags().clear(); // Clear existing hashtags
               postRepository.save(post); // Save post with updated hashtags
               return updatedHashtags; // Return empty set since no hashtags are updated
          }

          Set<String> reqHashtagsNames = updateReqHashtags.stream()
                    .map(Hashtag::getHashtagName)
                    .collect(Collectors.toSet());

          Set<String> existingHashtagsNames = post.getHashtags().stream()
                    .map(Hashtag::getHashtagName)
                    .collect(Collectors.toSet());

          System.out.println(
                    "reqHashtagsNames " + reqHashtagsNames + "\nexistingHashtagsNames " + existingHashtagsNames);
          // Case 2: Update or add new hashtags
          // Note - On adding new hashtag make sure you add it with existing hashtags
          // lists from frontend, dont just send newly added hashtag object , it will
          // replace all existing ones by only newly added.
          for (String hashtagName : reqHashtagsNames) {
               // check hashtag globally exists or not instead of checking in any post
               Hashtag hashtagExistsGlobally = this.hashtagRepository.findHashtagByName(hashtagName);
               if (hashtagExistsGlobally == null) {
                    System.out.println("hashtag not present globally");
                    if (!existingHashtagsNames.contains(hashtagName)) {
                         System.out.println("\n\n DB does not have requested hashtag name");
                         // Create new hashtag does not exist in post
                         Hashtag newHashtag = new Hashtag();
                         newHashtag.setHashtagName(hashtagName);
                         newHashtag.getPosts().add(post);
                         post.getHashtags().add(newHashtag); // Add to post entity
                         updatedHashtags.add(newHashtag); // Track newly added hashtag
                    }
               } else {
                    System.out.println("hashtag present globally");
                    hashtagExistsGlobally.getPosts().add(post);
                    post.getHashtags().add(hashtagExistsGlobally);
                    updatedHashtags.add(hashtagExistsGlobally);
               }
          }

          System.out.println("\n\n\n" + updatedHashtags);

          // Case 3: Delete hashtags not present in the update request
          Set<Hashtag> hashtagsToRemove = post.getHashtags().stream()
                    .filter(hashtag -> !reqHashtagsNames.contains(hashtag.getHashtagName()))
                    .collect(Collectors.toSet());

          System.out.println("\n\n\n" + hashtagsToRemove);

          post.getHashtags().removeAll(hashtagsToRemove); // Remove from post entity
          return post.getHashtags();
     }

     public void addManyHashtagsToPost(Post post, Set<Hashtag> hashtagsToAdd) {
          for (Hashtag hashtag : hashtagsToAdd) {
               post.getHashtags().add(hashtag);
          }
          this.postRepository.save(post);
     }

     public Set<Post> findHashtagPosts(String hashtagName) {
          Set<Post> hashtagPosts = this.hashtagRepository.findHashtagPosts(hashtagName);
          return hashtagPosts;
     }

     public void deleteHashtagByName(String hashtagName) {
          this.hashtagRepository.deleteByHashtagName(hashtagName);
     }

     public void deleteHashtagById(int hashtagId) {
          this.hashtagRepository.deleteById(hashtagId);
     }

}
