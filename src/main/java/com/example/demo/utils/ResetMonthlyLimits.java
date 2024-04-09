package com.example.demo.utils;

import com.example.demo.repository.AccountLimitsRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * Задача для Quartz по обнулению месячных лимитов
 */

@Slf4j
@AllArgsConstructor
public class ResetMonthlyLimits implements Job {

    private final AccountLimitsRepository accountLimitsRepository;


    public void execute(JobExecutionContext arg0) throws JobExecutionException {

        accountLimitsRepository.saveAllAndFlush(
            accountLimitsRepository
                .findAll()
                .parallelStream()
                .peek(x -> x.setLimitBalance(x.getLimit()))
                .toList()
        );
    }
}