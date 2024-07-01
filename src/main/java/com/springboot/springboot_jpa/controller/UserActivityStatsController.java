package com.springboot.springboot_jpa.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.springboot_jpa.entity.UserActivityStats;
import com.springboot.springboot_jpa.exceptions.UserNotFoundException;
import com.springboot.springboot_jpa.service.UserActivityStatsService;

@RestController
public class UserActivityStatsController {

     @Autowired
     private UserActivityStatsService userActivityStatsService;

     @GetMapping("/activity/{id}")
     public UserActivityStats findById(@PathVariable("id") Long activityId) {
          Optional<UserActivityStats> activity = this.userActivityStatsService.findById(activityId);
          if (activity.isEmpty()) {
               throw new UserNotFoundException("User with id " + activityId + " not found");
          }
          return activity.get();
     }

     @PostMapping("/activity")
     public UserActivityStats createNewUserActivityStats(@RequestBody UserActivityStats userActivityStats) {
          return this.userActivityStatsService.createNewUserActivityStats(userActivityStats);
     }

}
