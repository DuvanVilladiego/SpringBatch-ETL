package com.example.app.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.app.model.User;
import com.example.app.processor.ImportUserProcessor;
import com.example.app.utils.Constants;

import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class ImportUserJobConfig {

	@Autowired
	@Qualifier(Constants.IMPORT_USER_READER_BEAN)
	private FlatFileItemReader<User> reader;

	@Autowired
	@Qualifier(Constants.IMPORT_USER_PROCESSOR_BEAN)
	private ImportUserProcessor processor;

	@Autowired
	@Qualifier(Constants.IMPORT_USER_WRITER_BEAN)
	private JdbcBatchItemWriter<User> writer;

	@Bean
	public Step step1(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
		return new StepBuilder(Constants.IMPORT_USER_JOB_BEAN, jobRepository).<User, User>chunk(5, transactionManager)
				.reader(reader).processor(processor).writer(writer).build();
	}

	@Bean(Constants.IMPORT_USER_JOB_BEAN)
	@Autowired
	public Job importUserJob(JobRepository jobRepository, Step step1) {
		return new JobBuilder(Constants.IMPORT_USER_JOB_BEAN, jobRepository).start(step1).build();
	}

}
