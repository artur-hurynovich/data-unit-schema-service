package com.hurynovich.data_unit_schema_service.request_handler;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.ApiModel;
import com.hurynovich.data_unit_schema_service.model.Identified;
import com.hurynovich.data_unit_schema_service.model.ServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl_;
import com.hurynovich.data_unit_schema_service.service.BaseService;
import com.hurynovich.data_unit_schema_service.validator.Validator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

public abstract class AbstractBaseRequestHandler<T extends ApiModel<I>, U extends ServiceModel<I>, I extends Serializable>
        implements BaseRequestHandler<T, U, I> {

    private final Validator<T> validator;

    private final BaseService<U, I> service;

    private final ApiConverter<T, U> converter;

    protected AbstractBaseRequestHandler(@NonNull final Validator<T> validator,
                                         @NonNull final BaseService<U, I> service,
                                         @NonNull final ApiConverter<T, U> converter) {
        this.validator = validator;
        this.service = service;
        this.converter = converter;
    }

    @Override
    @NonNull
    public Mono<ServerResponse> post(@NonNull final ServerRequest request) {
        return request
                .bodyToMono(getApiModelClass())
                .flatMap(schema -> {
                            final Mono<ServerResponse> response;
                            if (schema.getId() != null) {
                                response = ServerResponse.badRequest().bodyValue(List.of("'id' should be null"));
                            } else {
                                final ValidationResult result = validator.validate(schema);
                                if (result.getType() == ValidationResultType.SUCCESS) {
                                    response = service
                                            .save(converter.convert(schema))
                                            .flatMap(s -> ServerResponse.created(buildLocation(request, s))
                                                    .bodyValue(s));
                                } else {
                                    response = ServerResponse.badRequest().bodyValue(result.getErrors());
                                }
                            }

                            return response;
                        }
                );
    }

    protected abstract Class<T> getApiModelClass();

    private URI buildLocation(@NonNull final ServerRequest request, @NonNull final Identified<?> target) {
        final String locationUriString = request.uri().toASCIIString() + "/" + target.getId();

        return URI.create(locationUriString);
    }

    @Override
    @NonNull
    public Mono<ServerResponse> getById(@NonNull final ServerRequest request) {
        final I id = convertPathVariableToId(request.pathVariable(DataUnitSchemaApiModelImpl_.ID));

        return service.findById(id)
                .flatMap(schema -> ServerResponse.ok().bodyValue(converter.convert(schema)));
    }

    protected abstract I convertPathVariableToId(final String pathVariable);

    @Override
    @NonNull
    public Mono<ServerResponse> put(@NonNull final ServerRequest request) {
        return request
                .bodyToMono(getApiModelClass())
                .flatMap(schema -> {
                            final Mono<ServerResponse> response;
                            final I id = schema.getId();
                            if (id == null) {
                                response = ServerResponse.badRequest().bodyValue(List.of("'id' can't be null"));
                            } else if (!id.equals(
                                    convertPathVariableToId(request.pathVariable(DataUnitSchemaApiModelImpl_.ID)))) {
                                response = ServerResponse.badRequest()
                                        .bodyValue(List.of("'id' should be equal to path variable"));
                            } else {
                                final ValidationResult result = validator.validate(schema);
                                if (result.getType() == ValidationResultType.SUCCESS) {
                                    response = service
                                            .save(converter.convert(schema))
                                            .flatMap(s -> ServerResponse
                                                    .ok()
                                                    .bodyValue(s));
                                } else {
                                    response = ServerResponse.badRequest().bodyValue(result.getErrors());
                                }
                            }

                            return response;
                        }
                );
    }

    @Override
    @NonNull
    public Mono<ServerResponse> deleteById(@NonNull final ServerRequest request) {
        final I id = convertPathVariableToId(request.pathVariable(DataUnitSchemaApiModelImpl_.ID));

        return service.deleteById(id)
                .flatMap(schema -> ServerResponse.ok().bodyValue(converter.convert(schema)));
    }
}
