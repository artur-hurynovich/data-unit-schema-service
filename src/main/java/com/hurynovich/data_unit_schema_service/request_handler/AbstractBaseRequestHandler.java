package com.hurynovich.data_unit_schema_service.request_handler;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.ApiModel;
import com.hurynovich.data_unit_schema_service.model.Identified;
import com.hurynovich.data_unit_schema_service.model.ServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl_;
import com.hurynovich.data_unit_schema_service.service.BaseService;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.net.URI;

public abstract class AbstractBaseRequestHandler<T extends ApiModel<I>, U extends ServiceModel<I>, I extends Serializable>
        implements BaseRequestHandler<T, U, I> {

    private final BaseService<U, I> service;

    private final ApiConverter<T, U> converter;

    protected AbstractBaseRequestHandler(final BaseService<U, I> service, final ApiConverter<T, U> converter) {
        this.service = service;
        this.converter = converter;
    }

    @Override
    @NonNull
    public Mono<ServerResponse> post(@NonNull final ServerRequest request) {
        return request
                .bodyToMono(getApiModelClass())
                .flatMap(schema ->
                        service
                                .save(converter.convert(schema))
                                .flatMap(s -> ServerResponse.created(buildLocation(request, s))
                                        .bodyValue(s)));
    }

    protected abstract Class<T> getApiModelClass();

    private URI buildLocation(@NonNull final ServerRequest request, @NonNull final Identified<?> target) {
        final String locationUriString = request.uri().toASCIIString() + "/" + target.getId();

        return URI.create(locationUriString);
    }

    @Override
    @NonNull
    public Mono<ServerResponse> getById(@NonNull final ServerRequest request) {
        final I id = convertPathVariableToId(request.pathVariable(DataUnitSchemaApiModelImpl_.ID));// ???

        return service.findById(id)
                .flatMap(schema -> ServerResponse.ok().bodyValue(converter.convert(schema)));
    }

    protected abstract I convertPathVariableToId(final String pathVariable);

    @Override
    @NonNull
    public Mono<ServerResponse> put(@NonNull final ServerRequest request) {
        return request
                .bodyToMono(getApiModelClass())
                .flatMap(schema ->
                        service
                                .save(converter.convert(schema))
                                .flatMap(s -> ServerResponse
                                        .ok()
                                        .bodyValue(s)));
    }

    @Override
    @NonNull
    public Mono<ServerResponse> deleteById(@NonNull final ServerRequest request) {
        final I id = convertPathVariableToId(request.pathVariable(DataUnitSchemaApiModelImpl_.ID));

        return service.deleteById(id)
                .flatMap(schema -> ServerResponse.ok().bodyValue(converter.convert(schema)));
    }
}
