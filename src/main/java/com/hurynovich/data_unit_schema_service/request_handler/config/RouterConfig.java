package com.hurynovich.data_unit_schema_service.request_handler.config;

import com.hurynovich.data_unit_schema_service.request_handler.DataUnitPropertySchemaRequestHandler;
import com.hurynovich.data_unit_schema_service.request_handler.DataUnitSchemaRequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(@NonNull final DataUnitSchemaRequestHandler schemaHandler,
                                                         @NonNull final DataUnitPropertySchemaRequestHandler propertySchemaHandler) {
        return RouterFunctions
                .route(RequestPredicates.POST("/v1.0/data_unit_schema"), schemaHandler::post)
                .andRoute(RequestPredicates.GET("/v1.0/data_unit_schema/{id}"), schemaHandler::getById)
                .andRoute(RequestPredicates.GET("/v1.0/data_unit_schemas"), schemaHandler::getAll)
                .andRoute(RequestPredicates.PUT("/v1.0/data_unit_schema/{id}"), schemaHandler::put)
                .andRoute(RequestPredicates.DELETE("/v1.0/data_unit_schema/{id}"), schemaHandler::deleteById)
                .andRoute(RequestPredicates.POST("/v1.0/data_unit_property_schema"), propertySchemaHandler::post)
                .andRoute(RequestPredicates.GET("/v1.0/data_unit_property_schema/{id}"), propertySchemaHandler::getById)
                .andRoute(RequestPredicates.GET("/v1.0/data_unit_property_schemas"), propertySchemaHandler::getAllBySchemaId)
                .andRoute(RequestPredicates.PUT("/v1.0/data_unit_property_schema/{id}"), propertySchemaHandler::put)
                .andRoute(RequestPredicates.DELETE("/v1.0/data_unit_property_schema/{id}"), propertySchemaHandler::deleteById);
    }
}
