package com.hurynovich.data_unit_schema_service.request_handler.config;

import com.hurynovich.data_unit_schema_service.request_handler.DataUnitSchemaRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class DataUnitSchemaRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(@NonNull final DataUnitSchemaRequestHandler handler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/v1.0/data_unit_schema"), handler::postSchema)
                .andRoute(RequestPredicates.GET("/v1.0/data_unit_schema/{id}"), handler::getSchemaById)
                .andRoute(RequestPredicates.GET("/v1.0/data_unit_schemas"), handler::getAllSchemas)
                .andRoute(RequestPredicates.PUT("/v1.0/data_unit_schema/{id}"), handler::putSchema)
                .andRoute(RequestPredicates.DELETE("/v1.0/data_unit_schema/{id}"), handler::deleteSchemaById);
    }
}
