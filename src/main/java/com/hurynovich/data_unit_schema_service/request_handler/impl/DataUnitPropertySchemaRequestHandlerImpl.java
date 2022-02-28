package com.hurynovich.data_unit_schema_service.request_handler.impl;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl_;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.request_handler.AbstractBaseRequestHandler;
import com.hurynovich.data_unit_schema_service.request_handler.DataUnitPropertySchemaRequestHandler;
import com.hurynovich.data_unit_schema_service.service.DataUnitPropertySchemaService;
import com.hurynovich.data_unit_schema_service.utils.MassProcessingUtils;
import com.hurynovich.data_unit_schema_service.validator.Validator;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Service
public class DataUnitPropertySchemaRequestHandlerImpl
        extends AbstractBaseRequestHandler<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel, String>
        implements DataUnitPropertySchemaRequestHandler {

    private final DataUnitPropertySchemaService service;

    private final ApiConverter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel> converter;

    public DataUnitPropertySchemaRequestHandlerImpl(@NonNull final Validator<DataUnitPropertySchemaApiModel> validator,
                                                    @NonNull final DataUnitPropertySchemaService service,
                                                    @NonNull final ApiConverter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel> converter) {
        super(validator, service, converter);

        this.service = service;
        this.converter = converter;
    }

    @Override
    @NonNull
    public Mono<ServerResponse> getAllBySchemaId(@NonNull final ServerRequest request) {
        return Mono.justOrEmpty(request.queryParam(DataUnitPropertySchemaApiModelImpl_.SCHEMA_ID))
                .flatMap(schemaId -> service.findAllBySchemaId(schemaId)
                        .flatMap(schemas -> ServerResponse.ok().bodyValue(
                                MassProcessingUtils.processQuietly(schemas, converter::convert)
                        )));
    }

    @Override
    protected Class<DataUnitPropertySchemaApiModel> getApiModelClass() {
        return DataUnitPropertySchemaApiModel.class;
    }

    @Override
    protected String convertPathVariableToId(final String pathVariable) {
        return pathVariable;
    }
}
