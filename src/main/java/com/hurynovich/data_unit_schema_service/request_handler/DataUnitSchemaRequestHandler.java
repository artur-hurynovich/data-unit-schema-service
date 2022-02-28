package com.hurynovich.data_unit_schema_service.request_handler;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface DataUnitSchemaRequestHandler
        extends BaseRequestHandler<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, String> {

    @NonNull
    Mono<ServerResponse> getAll(@NonNull ServerRequest request);
}
