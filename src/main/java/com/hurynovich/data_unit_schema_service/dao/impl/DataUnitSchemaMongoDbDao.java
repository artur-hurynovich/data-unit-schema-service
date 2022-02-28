package com.hurynovich.data_unit_schema_service.dao.impl;

import com.hurynovich.data_unit_schema_service.dao.AbstractBaseDao;
import com.hurynovich.data_unit_schema_service.dao.DataUnitSchemaDao;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataUnitSchemaMongoDbDao extends AbstractBaseDao<DataUnitSchemaPersistentModel, String>
        implements DataUnitSchemaDao {

    public DataUnitSchemaMongoDbDao(@NonNull final ReactiveMongoTemplate template) {
        super(DataUnitSchemaDocument.class, template);
    }

    @Override
    public Mono<List<DataUnitSchemaPersistentModel>> findAll(@NonNull final PaginationParams params) {
        final Query query = new Query()
                .skip(params.offset())
                .limit(params.limit());

        return template
                .find(query, DataUnitSchemaDocument.class)
                .collectList()
                .flatMap(schemas -> Mono.justOrEmpty(
                        schemas.stream()
                                .map(schema -> (DataUnitSchemaPersistentModel) schema)
                                .collect(Collectors.toList())));
    }
}
