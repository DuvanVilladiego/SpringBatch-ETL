package com.example.app.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.app.model.User;
import com.example.app.utils.Constants;
import com.example.app.utils.Methods;

@Component(Constants.IMPORT_USER_PROCESSOR_BEAN)
public class ImportUserProcessor implements ItemProcessor<User, User> {

	private static final Logger LOG = LoggerFactory.getLogger(ImportUserProcessor.class);

	@Override
	public User process(User item) throws Exception {
		LOG.info(Constants.STARTING_CSV_PROCESSING);
		String name = item.getName().toUpperCase();
		String email = item.getEmail();
		if (!Methods.isValidEmail(item.getEmail())) {
			LOG.error(String.format(Constants.INVALID_EMAIL_FORMAT, item.getId()));
			email = "";
		}
		User user = new User(item.getId(), name, email);
		LOG.warn(String.format(Constants.OVERWRITING_WARNING, item, user));
		LOG.info(Constants.FINISHED_CSV_PROCESSING);
		return user;
	}

}
