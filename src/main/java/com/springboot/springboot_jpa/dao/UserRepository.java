package com.springboot.springboot_jpa.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.springboot_jpa.entity.Post;
import com.springboot.springboot_jpa.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

     // Derived query method

     ArrayList<User> findByName(String name);

     ArrayList<User> findByNameAndCity(String name, String City);

     // JPQL - Java persistence query lanaguage
     @Query("select u from User u")
     public List<User> getAllUsers();

     @Query("select u.name from User u")
     public List<String> getAllUsersName();

     @Query("select u from User u where u.name =:n and u.city =:c")
     public List<User> getUserDataByName(@Param("n") String name, @Param("c") String city);

     // Native query -> Means we write SQL query and to understand them we pass
     // nativeQuery parameter = true
     @Query(value = "select * from User", nativeQuery = true)
     public List<User> getAllUsersByNativeQuery();

     // mapping queries
     @Query("select u.posts from User u where u.userId =:n")
     public List<Post> getUserPosts(@PathVariable("userId") int n);
}
