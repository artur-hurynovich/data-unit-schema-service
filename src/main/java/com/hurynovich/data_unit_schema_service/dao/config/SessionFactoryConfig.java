package com.hurynovich.data_unit_schema_service.dao.config;

import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Persistence;

@Configuration
public class SessionFactoryConfig {

    private static final String PERSISTENCE_UNIT_NAME = "dataUnitSchemaServicePU";

    @Bean
    public Mutiny.SessionFactory sessionFactory() {
        return Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .unwrap(Mutiny.SessionFactory.class);
    }
}
