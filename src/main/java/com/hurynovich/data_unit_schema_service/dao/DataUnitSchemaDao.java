package com.hurynovich.data_unit_schema_service.dao;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.paginator.PaginationParams;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataUnitSchemaDao extends BaseDao<DataUnitSchemaPersistentModel, String> {

    Mono<List<DataUnitSchemaPersistentModel>> findAll(@NonNull PaginationParams params);
}
