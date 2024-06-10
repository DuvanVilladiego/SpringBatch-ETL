package com.example.app.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.example.app.model.User;
import com.example.app.utils.Constants;

@Component
public class ImportUserReader {

    @Bean(Constants.IMPORT_USER_READER_BEAN)
    public FlatFileItemReader<User> flatFileItemReader() {
        return new FlatFileItemReaderBuilder<User>()
                .name(Constants.IMPORT_USER_READER_BEAN)
                .resource(new ClassPathResource(Constants.CSV_CLASSPATH))
                .linesToSkip(1)
                .delimited()
                .delimiter(Constants.CSV_DELIMITER)
                .names(new String[]{"id", "name", "email"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                    setTargetType(User.class);
                }})
                .build();
    }
	
}
