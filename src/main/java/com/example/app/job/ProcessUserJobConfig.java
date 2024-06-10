package com.example.app.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.app.model.User;
import com.example.app.processor.ProcessUserProcessor;
import com.example.app.utils.Constants;

@Configuration
public class ProcessUserJobConfig {

	@Autowired
	@Qualifier(Constants.PROCESS_JOB_READER_BEAN)
	private JdbcCursorItemReader<User> reader;

	@Autowired
	@Qualifier(Constants.PROCESS_JOB_PROCESSOR_BEAN)
	private ProcessUserProcessor processor;

	@Autowired
	@Qualifier(Constants.PROCESS_JOB_WRITER_BEAN)
	private MongoItemWriter<User> writer;

	@Bean
	public Step step2(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder(Constants.PROCESS_JOB_BEAN, jobRepository).<User, User>chunk(10, transactionManager)
				.reader(reader).processor(processor).writer(writer).build();
	}

	@Bean(Constants.PROCESS_JOB_BEAN)
	@Autowired
	public Job processUserJob(JobRepository jobRepository, Step step2) {
		return new JobBuilder(Constants.PROCESS_JOB_BEAN, jobRepository).start(step2).build();
	}
}
