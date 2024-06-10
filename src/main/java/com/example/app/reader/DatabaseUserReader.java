package com.example.app.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.example.app.model.User;

@Component
public class DatabaseUserReader {

    @Bean
    public JdbcCursorItemReader<User> jdbcCursorItemReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<User>()
                .name("userJdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("SELECT id, name, email FROM users")
                .rowMapper(new BeanPropertyRowMapper<>(User.class))
                .build();
    }
	
}
