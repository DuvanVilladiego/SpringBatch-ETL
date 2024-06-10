package com.example.app.writer;

import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.data.builder.MongoItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.example.app.model.User;
import com.example.app.utils.Constants;

@Component
public class ProcessUserWriter {

	@Bean(Constants.PROCESS_JOB_WRITER_BEAN)
    public MongoItemWriter<User> processedUserWriter(MongoTemplate mongoTemplate) {
        return new MongoItemWriterBuilder<User>()
                .template(mongoTemplate)
                .collection(Constants.MONGO_COLLECTION_PROCESS_USERS)
                .build();
    }
}
