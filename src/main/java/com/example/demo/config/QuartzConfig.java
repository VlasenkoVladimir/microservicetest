package com.example.demo.config;

import com.example.demo.utils.ResetMonthlyLimits;
import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.springframework.context.annotation.Configuration;

/**
 * Задача для Quartz по обнулению месячных лимитов
 */

@Configuration
public class QuartzConfig {

    @PostConstruct
    public void setUp() {

        JobDetail resetMonthlyLimits = JobBuilder.newJob(ResetMonthlyLimits.class)
            .build();

        CronTrigger trigger = TriggerBuilder.newTrigger()
            .withIdentity("ResetMonthlyLimits", "AccountLimitsGroup")
            .withSchedule(CronScheduleBuilder.cronSchedule("${crontab.interval-in-cron}"))
            .forJob("resetMonthlyLimits", "AccountLimitsGroup")
            .build();
    }
}