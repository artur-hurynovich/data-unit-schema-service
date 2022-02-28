package com.hurynovich.data_unit_schema_service.request_handler;

import com.hurynovich.data_unit_schema_service.model.ApiModel;
import com.hurynovich.data_unit_schema_service.model.ServiceModel;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public interface BaseRequestHandler<T extends ApiModel<I>, U extends ServiceModel<I>, I extends Serializable> {

    @NonNull
    Mono<ServerResponse> post(@NonNull ServerRequest request);

    @NonNull
    Mono<ServerResponse> getById(@NonNull ServerRequest request);

    @NonNull
    Mono<ServerResponse> put(@NonNull ServerRequest request);

    @NonNull
    Mono<ServerResponse> deleteById(@NonNull ServerRequest request);
}
