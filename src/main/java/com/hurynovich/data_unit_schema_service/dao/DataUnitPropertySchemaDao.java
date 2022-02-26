package com.hurynovich.data_unit_schema_service.dao;

import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.util.List;

public interface DataUnitPropertySchemaDao extends BaseDao<DataUnitPropertySchemaPersistentModel, String> {

    Mono<List<DataUnitPropertySchemaPersistentModel>> findAllBySchemaId(@NonNull PaginationParams params,
                                                                        @NonNull String schemaId);
}
