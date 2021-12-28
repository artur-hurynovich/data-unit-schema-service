package com.hurynovich.data_unit_schema_service.service;

import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataUnitSchemaService {

    Mono<DataUnitSchemaServiceModel> save(@NonNull DataUnitSchemaServiceModel schema);

    Mono<DataUnitSchemaServiceModel> findById(@NonNull Long id);

    Mono<List<DataUnitSchemaServiceModel>> findAll(@NonNull PaginationParams params);

    Mono<Void> deleteById(@NonNull Long id);

    Mono<Long> count();
}
