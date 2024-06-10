package com.example.app.reader;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.example.app.model.User;

@Component
public class CsvUserListener {

    @Bean
    public FlatFileItemReader<User> flatFileItemReader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .resource(new FileSystemResource("C:\\Users\\duvan\\OneDrive\\Desktop\\batch\\etl-migration\\src\\main\\resources\\archivo.csv"))
                .linesToSkip(1)
                .delimited()
                .delimiter(",")
                .names(new String[]{"id", "name", "email"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
                    setTargetType(User.class);
                }})
                .build();
    }
	
}
