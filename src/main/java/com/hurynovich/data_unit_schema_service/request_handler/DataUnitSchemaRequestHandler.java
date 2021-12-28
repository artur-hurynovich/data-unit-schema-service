package com.hurynovich.data_unit_schema_service.request_handler;

import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface DataUnitSchemaRequestHandler {

    Mono<ServerResponse> postSchema(@NonNull ServerRequest request);

    Mono<ServerResponse> getSchemaById(@NonNull ServerRequest request);

    Mono<ServerResponse> getAllSchemas(@NonNull ServerRequest request);

    Mono<ServerResponse> putSchema(@NonNull ServerRequest request);

    Mono<ServerResponse> deleteSchemaById(@NonNull ServerRequest request);
}
