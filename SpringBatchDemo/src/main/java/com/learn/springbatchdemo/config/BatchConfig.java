package com.learn.springbatchdemo.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    //创建任务的工厂[job由step组成]
    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    //创建步骤的工厂
    @Autowired
    private StepBuilderFactory stepBuilderFactory;






}
