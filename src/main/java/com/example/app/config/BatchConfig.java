package com.example.app.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.example.app.utils.Constants;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfig {

	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier(Constants.IMPORT_USER_JOB_BEAN)
	private Job importUserJob;

	@Autowired
	@Qualifier(Constants.PROCESS_JOB_BEAN)
	private Job processUserJob;

	@Scheduled(fixedRateString = "10000")
	public void executeImportJob() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();
		jobLauncher.run(importUserJob, jobParameters);
	}

	@Scheduled(fixedRateString = "15000")
	public void executeProcessJob() throws Exception {
		JobParameters jobParameters = new JobParametersBuilder()
				.addString("JobID", String.valueOf(System.currentTimeMillis())).toJobParameters();
		jobLauncher.run(processUserJob, jobParameters);
	}

}