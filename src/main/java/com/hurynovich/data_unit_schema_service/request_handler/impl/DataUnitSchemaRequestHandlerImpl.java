package com.hurynovich.data_unit_schema_service.request_handler.impl;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.Identified;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.paginator.Paginator;
import com.hurynovich.data_unit_schema_service.request_handler.DataUnitSchemaRequestHandler;
import com.hurynovich.data_unit_schema_service.service.DataUnitSchemaService;
import com.hurynovich.data_unit_schema_service.utils.MassProcessingUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Service
class DataUnitSchemaRequestHandlerImpl implements DataUnitSchemaRequestHandler {

    public static final String PAGE_NUMBER_REQUEST_PARAM = "pageNumber";

    private static final int DATA_UNIT_SCHEMAS_PER_PAGE = 20;

    private final DataUnitSchemaService service;

    private final ApiConverter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel> converter;

    private final Paginator paginator;

    public DataUnitSchemaRequestHandlerImpl(@NonNull final DataUnitSchemaService service,
                                            @NonNull final ApiConverter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel> converter,
                                            @NonNull final Paginator paginator) {
        this.service = service;
        this.converter = converter;
        this.paginator = paginator;
    }

    @Override
    public Mono<ServerResponse> postSchema(@NonNull final ServerRequest request) {
        return request
                .bodyToMono(DataUnitSchemaApiModel.class)
                .flatMap(schema ->
                        service
                                .save(converter.convert(schema))
                                .flatMap(s -> ServerResponse.created(buildLocation(request, s))
                                        .bodyValue(s)));
    }

    private URI buildLocation(@NonNull final ServerRequest request, @NonNull final Identified<?> target) {
        final String locationUriString = request.uri().toASCIIString() + "/" + target.getId();

        return URI.create(locationUriString);
    }

    @Override
    public Mono<ServerResponse> getSchemaById(@NonNull final ServerRequest request) {
        final String id = request.pathVariable(DataUnitSchemaApiModelImpl_.ID);

        return service.findById(id)
                .flatMap(schema -> ServerResponse.ok().bodyValue(converter.convert(schema)));
    }

    @Override
    public Mono<ServerResponse> getAllSchemas(@NonNull final ServerRequest request) {
        return Mono.justOrEmpty(request.queryParam(PAGE_NUMBER_REQUEST_PARAM))
                .defaultIfEmpty("1")
                .flatMap(pageNumber -> {
                    final PaginationParams params = paginator.buildParams(
                            Integer.valueOf(pageNumber), DATA_UNIT_SCHEMAS_PER_PAGE);

                    return Mono.zip(service.findAll(params), service.count(),
                                    (schemas, count) -> {
                                        final List<DataUnitSchemaApiModel> convertedSchemas = MassProcessingUtils
                                                .processQuietly(schemas, converter::convert);

                                        return paginator.buildPage(convertedSchemas, count, params);
                                    })
                            .flatMap(page -> ServerResponse.ok().bodyValue(page));
                });
    }

    @Override
    public Mono<ServerResponse> putSchema(@NonNull final ServerRequest request) {
        return request
                .bodyToMono(DataUnitSchemaApiModel.class)
                .flatMap(schema ->
                        service
                                .save(converter.convert(schema))
                                .flatMap(s -> ServerResponse
                                        .ok()
                                        .bodyValue(s)));
    }

    @Override
    public Mono<ServerResponse> deleteSchemaById(@NonNull final ServerRequest request) {
        final String id = request.pathVariable(DataUnitSchemaApiModelImpl_.ID);

        return service.deleteById(id)
                .flatMap(schema -> ServerResponse.ok().bodyValue(converter.convert(schema)));
    }
}
