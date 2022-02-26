package com.hurynovich.data_unit_schema_service.dao.impl;

import com.hurynovich.data_unit_schema_service.dao.DataUnitSchemaDao;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaDocument_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataUnitSchemaMongoDbDao implements DataUnitSchemaDao {

    private final ReactiveMongoTemplate template;

    public DataUnitSchemaMongoDbDao(@NonNull final ReactiveMongoTemplate template) {
        this.template = template;
    }

    @Override
    public Mono<DataUnitSchemaPersistentModel> save(@NonNull final DataUnitSchemaPersistentModel schema) {
        return template.save(schema);
    }

    @Override
    public Mono<DataUnitSchemaPersistentModel> findById(@NonNull final String id) {
        return template
                .findById(id, DataUnitSchemaDocument.class)
                .flatMap(schema -> Mono.justOrEmpty((DataUnitSchemaPersistentModel) schema));
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

    @Override
    public Mono<DataUnitSchemaPersistentModel> deleteById(@NonNull final String id) {
        final Query query = new Query()
                .addCriteria(Criteria.where(DataUnitSchemaDocument_.ID).is(id));

        return template.findAndRemove(query, DataUnitSchemaDocument.class)
                .flatMap(schema -> Mono.justOrEmpty((DataUnitSchemaPersistentModel) schema));
    }

    @Override
    public Mono<Long> count() {
        return template.count(new Query(), DataUnitSchemaDocument.class);
    }
}
