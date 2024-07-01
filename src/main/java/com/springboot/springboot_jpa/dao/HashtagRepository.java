package com.springboot.springboot_jpa.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.springboot_jpa.entity.Hashtag;

@Repository
public interface HashtagRepository extends CrudRepository<Hashtag, Integer> {

}
