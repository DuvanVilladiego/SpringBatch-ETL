package com.example.app.utils;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.example.app.model.User;

public class UserFieldSetMapper implements FieldSetMapper<User> {
    @Override
    public User mapFieldSet(FieldSet fieldSet) {
        User user = new User();
        user.setId(fieldSet.readInt("id"));
        user.setName(fieldSet.readString("name"));
        user.setEmail(fieldSet.readString("email"));
        return user;
    }
}