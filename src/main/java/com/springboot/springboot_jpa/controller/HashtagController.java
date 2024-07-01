package com.springboot.springboot_jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot_jpa.service.HashtagService;

@RestController
public class HashtagController {
     @Autowired
     private HashtagService hashtagService;

     public HashtagController(HashtagService hashtagService) {
          this.hashtagService = hashtagService;
     }

     

}
