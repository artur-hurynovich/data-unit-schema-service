package com.hurynovich.data_unit_schema_service.dao.config;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.lang.NonNull;

@Configuration
public class ReactiveMongoDbConfig {

    @Value("dataUnitSchemaDbName")
    private String dataUnitSchemaDbName;

    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate(@NonNull final MongoClient client) {
        return new ReactiveMongoTemplate(client, dataUnitSchemaDbName);
    }
}
