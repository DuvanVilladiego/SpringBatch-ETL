package com.example.app.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.example.app.model.User;
import com.example.app.utils.Constants;

@Component
public class ImportUserWriter {

	@Bean(Constants.IMPORT_USER_WRITER_BEAN)
	public JdbcBatchItemWriter<User> jdbcBatchItemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<User>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql(Constants.INSERT_AND_UPDATE_INTO_USERS)
				.dataSource(dataSource).build();
	}

}
