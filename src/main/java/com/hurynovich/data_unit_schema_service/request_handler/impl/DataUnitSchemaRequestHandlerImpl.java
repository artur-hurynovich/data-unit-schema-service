package com.hurynovich.data_unit_schema_service.request_handler.impl;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.paginator.Paginator;
import com.hurynovich.data_unit_schema_service.request_handler.AbstractBaseRequestHandler;
import com.hurynovich.data_unit_schema_service.request_handler.DataUnitSchemaRequestHandler;
import com.hurynovich.data_unit_schema_service.service.DataUnitSchemaService;
import com.hurynovich.data_unit_schema_service.utils.MassProcessingUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
class DataUnitSchemaRequestHandlerImpl extends AbstractBaseRequestHandler<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, String>
        implements DataUnitSchemaRequestHandler {

    private static final String PAGE_NUMBER_REQUEST_PARAM = "pageNumber";

    private static final int DATA_UNIT_SCHEMAS_PER_PAGE = 20;

    private final DataUnitSchemaService service;

    private final ApiConverter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel> converter;

    private final Paginator paginator;

    public DataUnitSchemaRequestHandlerImpl(@NonNull final DataUnitSchemaService service,
                                            @NonNull final ApiConverter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel> converter,
                                            @NonNull final Paginator paginator) {
        super(service, converter);

        this.service = service;
        this.converter = converter;
        this.paginator = paginator;
    }

    @Override
    @NonNull
    public Mono<ServerResponse> getAll(@NonNull final ServerRequest request) {
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
    protected Class<DataUnitSchemaApiModel> getApiModelClass() {
        return DataUnitSchemaApiModel.class;
    }

    @Override
    protected String convertPathVariableToId(final String pathVariable) {
        return pathVariable;
    }
}
