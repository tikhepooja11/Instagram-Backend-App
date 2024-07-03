package com.springboot.springboot_jpa.dao;

import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.springboot.springboot_jpa.entity.Hashtag;
import com.springboot.springboot_jpa.entity.Post;

@Repository
public interface HashtagRepository extends CrudRepository<Hashtag, Integer> {

     public void deleteByHashtagName(String hashtagName);

     @Query("select h from Hashtag h where h.hashtagName =:n")
     public Hashtag findHashtagByName(@Param("n") String hashtagName);

     @Query("select h.posts from Hashtag h where h.hashtagName =:n")
     public Set<Post> findHashtagPosts(@Param("n") String hashtagName);
}
