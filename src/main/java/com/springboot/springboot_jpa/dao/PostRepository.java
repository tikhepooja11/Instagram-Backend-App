package com.springboot.springboot_jpa.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import com.springboot.springboot_jpa.entity.Hashtag;
import com.springboot.springboot_jpa.entity.Post;
import com.springboot.springboot_jpa.entity.User;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
     @Query("select p.user from Post p where p.postId =:n")
     public User findPostsUser(@PathVariable("postId") int n);

     @Query("select p.hashtags from Post p where p.postId =:n")
     public Set<Hashtag> findPostsHashtags(@PathVariable("postId") int n);

     @Query("SELECT DISTINCT h FROM Post p JOIN p.hashtags h WHERE p.postId IN :postIds")
     public Set<Hashtag> findMultiplePostsHashtags(@Param("postIds") List<Integer> postsIds);

     // @Query("delete from Post p where p.postId =:n")
     // public void deletePost(@PathVariable("postId") int n);


}
