package com.example.app.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.app.model.User;

@Component
public class DatabaseUserWriter {
    
	@Bean
    public JdbcBatchItemWriter<User> jdbcBatchItemWriter(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<User>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO users (id, name, email) VALUES (:id, :name, :email) ON CONFLICT DO NOTHING")
                .dataSource(dataSource)
                .build();
    }
}
