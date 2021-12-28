package com.hurynovich.data_unit_schema_service.dao;

import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataUnitSchemaDao {

    Mono<DataUnitSchemaPersistentModel> save(@NonNull DataUnitSchemaPersistentModel schema);

    Mono<DataUnitSchemaPersistentModel> findById(@NonNull Long id);

    Mono<List<DataUnitSchemaPersistentModel>> findAll(@NonNull PaginationParams params);

    Mono<Void> deleteById(@NonNull Long id);

    Mono<Long> count();
}
