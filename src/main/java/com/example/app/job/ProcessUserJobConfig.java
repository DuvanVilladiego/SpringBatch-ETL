package com.example.app.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.app.model.User;

@Configuration
public class ProcessUserJobConfig {

    @Bean
    public Step step2(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      JdbcCursorItemReader<User> reader,
                      ItemProcessor<User, User> processor,
                      JdbcBatchItemWriter<User> writer) {
        return new StepBuilder("ProcessUser", jobRepository)
                .<User, User>chunk(10, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    @Autowired
    public Job processUserJob(JobRepository jobRepository, Step step2) {
        return new JobBuilder("processUserJob", jobRepository)
                .start(step2)
                .build();
    }
}
