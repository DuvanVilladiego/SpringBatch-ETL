package com.example.app.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.app.model.User;
import com.example.app.utils.Constants;

@Component(Constants.PROCESS_JOB_PROCESSOR_BEAN)
public class ProcessUserProcessor implements ItemProcessor<User, User> {

	private static final Logger LOG = LoggerFactory.getLogger(ProcessUserProcessor.class);

	@Override
	public User process(User item) throws Exception {
		LOG.info(Constants.STARTING_ETL_PROCESSING);
		String name = String.format("%s_PROCESSED", item.getName());
		User user = new User(item.getId(), name, item.getEmail());
		LOG.info(Constants.FINISHED_ETL_PROCESSING);
		return user;
	}
}