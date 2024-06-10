package com.example.app.reader;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;

import com.example.app.model.User;
import com.example.app.utils.Constants;

@Component
public class ProcessUserReader {

    @Bean(Constants.PROCESS_JOB_READER_BEAN)
    public JdbcCursorItemReader<User> jdbcCursorItemReader(DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<User>()
                .name(Constants.PROCESS_JOB_READER_BEAN)
                .dataSource(dataSource)
                .sql(Constants.READ_USER_DATA_TO_PROCESS)
                .rowMapper(new BeanPropertyRowMapper<>(User.class))
                .build();
    }
	
}
