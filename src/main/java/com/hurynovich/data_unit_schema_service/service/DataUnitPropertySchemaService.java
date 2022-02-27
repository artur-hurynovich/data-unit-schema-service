package com.hurynovich.data_unit_schema_service.service;

import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataUnitPropertySchemaService extends BaseService<DataUnitPropertySchemaServiceModel, String> {

    Mono<List<DataUnitPropertySchemaServiceModel>> findAllBySchemaId(@NonNull String schemaId);
}
