package com.hurynovich.data_unit_schema_service.dao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.lang.NonNull;

@Configuration
@EnableReactiveMongoRepositories
public class ReactiveMongoDbConfig extends AbstractReactiveMongoConfiguration {

    @Value("dataUnitSchemaDbName")
    private String dataUnitSchemaDbName;

    @Override
    @NonNull
    protected String getDatabaseName() {
        return dataUnitSchemaDbName;
    }
}
