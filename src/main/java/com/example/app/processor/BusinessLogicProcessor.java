package com.example.app.processor;

import org.springframework.batch.item.ItemProcessor;

import com.example.app.model.User;

public class BusinessLogicProcessor implements ItemProcessor<User, User> {

    @Override
    public User process(User user) throws Exception {
        user.setName(user.getName() + "_PROCESSED");
        return user;
    }
}