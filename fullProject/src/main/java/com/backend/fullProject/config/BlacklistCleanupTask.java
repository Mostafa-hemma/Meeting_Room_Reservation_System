package com.backend.fullProject.config;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.backend.fullProject.dao.BlackListedTokenDao;

//this compnent is responsible for cleaning the blacklist of tokens in a schedule way for every period of time
@Component
public class BlacklistCleanupTask {

    @Autowired
    private BlackListedTokenDao blackListedTokenDao;

//    @Scheduled(cron = "0 0 0 * * ?")  // Runs daily at midnight
//    @Scheduled(cron = "0 */2 * * * *")  // Runs every 2 minutes
 //   @Scheduled(cron = "*/30 * * * * *")  // Runs every 30 seconds
   @Scheduled(cron = "0 0 0 * * SUN")  // Runs weekly at midnight on Sunday
    public void cleanUpExpiredTokens() {
    	blackListedTokenDao.deleteByExpirationBefore(LocalDateTime.now());
        System.out.println("Expired tokens cleaned up at: " + LocalDateTime.now());

    }
}
