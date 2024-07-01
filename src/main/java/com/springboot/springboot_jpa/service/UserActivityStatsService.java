package com.springboot.springboot_jpa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.springboot.springboot_jpa.dao.UserActivityStatsRepository;
import com.springboot.springboot_jpa.entity.UserActivityStats;

@Service
public class UserActivityStatsService {
     @Autowired
     private UserActivityStatsRepository userActivityStatsRepository;

     public UserActivityStatsService(UserActivityStatsRepository userActivityStatsRepository) {
          this.userActivityStatsRepository = userActivityStatsRepository;
     }

     public Optional<UserActivityStats> findById(Long activityId) {
          Optional<UserActivityStats> activityStats = this.userActivityStatsRepository.findById(activityId);
          System.out.println(activityStats.get());
          return activityStats;
     }

     public UserActivityStats createNewUserActivityStats(@RequestBody UserActivityStats userActivityStats) {
          return this.userActivityStatsRepository.save(userActivityStats);
     }

}
