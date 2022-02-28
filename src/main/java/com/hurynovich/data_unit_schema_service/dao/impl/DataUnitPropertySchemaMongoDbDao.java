package com.hurynovich.data_unit_schema_service.dao.impl;

import com.hurynovich.data_unit_schema_service.dao.AbstractBaseDao;
import com.hurynovich.data_unit_schema_service.dao.DataUnitPropertySchemaDao;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaDocument_;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DataUnitPropertySchemaMongoDbDao extends AbstractBaseDao<DataUnitPropertySchemaPersistentModel, String>
        implements DataUnitPropertySchemaDao {

    public DataUnitPropertySchemaMongoDbDao(@NonNull final ReactiveMongoTemplate template) {
        super(DataUnitPropertySchemaPersistentModel.class, template);
    }

    @Override
    public Mono<List<DataUnitPropertySchemaPersistentModel>> findAllBySchemaId(@NonNull final String schemaId) {
        final Query query = new Query()
                .addCriteria(Criteria.where(DataUnitPropertySchemaDocument_.SCHEMA_ID).is(schemaId));

        return template
                .find(query, DataUnitPropertySchemaDocument.class)
                .collectList()
                .flatMap(schemas -> Mono.justOrEmpty(
                        schemas.stream()
                                .map(schema -> (DataUnitPropertySchemaPersistentModel) schema)
                                .collect(Collectors.toList())));
    }
}
