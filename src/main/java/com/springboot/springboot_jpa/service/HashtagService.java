package com.springboot.springboot_jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.springboot_jpa.dao.HashtagRepository;

@Service
public class HashtagService {
     @Autowired
     private HashtagRepository hashtagRepository;

     public HashtagService(HashtagRepository hashtagRepository) {
          this.hashtagRepository = hashtagRepository;
     }

}
