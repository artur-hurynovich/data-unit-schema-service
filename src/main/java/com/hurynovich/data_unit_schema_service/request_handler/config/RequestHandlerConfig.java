package com.hurynovich.data_unit_schema_service.request_handler.config;

import com.hurynovich.paginator.Paginator;
import com.hurynovich.paginator.impl.PaginatorImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestHandlerConfig {

    @Bean
    public Paginator paginator() {
        return new PaginatorImpl();
    }
}
