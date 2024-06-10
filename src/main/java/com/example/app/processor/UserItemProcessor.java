package com.example.app.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.app.exception.ValidationException;
import com.example.app.model.User;
import com.example.app.utils.Methods;

@Component
public class UserItemProcessor implements ItemProcessor<User, User> {
	
	private static final Logger LOG  = LoggerFactory.getLogger(UserItemProcessor.class);

	@Override
	public User process(User item) throws Exception {
		LOG.info("INCIANDO PROCESAMIENTO DE CSV");
		String name = item.getName().toUpperCase();
		String email = item.getEmail();
		System.out.println(item.toString());
		if(!Methods.isValidEmail(item.getEmail())) {
            throw new ValidationException("Invalid email: " + item.getEmail());
		}
		User user = new User(item.getId(), name, email);
		LOG.info("SOBREESCRIBIENDO {"+item+"} A {"+user+"}");
		return user;
	}

}
