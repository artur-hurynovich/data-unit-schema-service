package com.hurynovich.data_unit_schema_service.request_handler;

import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface DataUnitPropertySchemaRequestHandler
        extends BaseRequestHandler<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel, String> {

    @NonNull
    Mono<ServerResponse> getAllBySchemaId(@NonNull ServerRequest request);
}
