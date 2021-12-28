package com.hurynovich.data_unit_schema_service.service.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.dao.DataUnitSchemaDao;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.service.DataUnitSchemaService;
import com.hurynovich.data_unit_schema_service.utils.MassProcessingUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
class DataUnitSchemaServiceImpl implements DataUnitSchemaService {

    private final DataUnitSchemaDao dao;

    private final ServiceConverter<DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> converter;

    public DataUnitSchemaServiceImpl(@NonNull final DataUnitSchemaDao dao,
                                     @NonNull final ServiceConverter<DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public Mono<DataUnitSchemaServiceModel> save(@NonNull final DataUnitSchemaServiceModel schema) {
        return dao.save(converter.convert(schema)).flatMap(s -> Mono.justOrEmpty(converter.convert(s, true)));
    }

    @Override
    public Mono<DataUnitSchemaServiceModel> findById(@NonNull final Long id) {
        return dao.findById(id).flatMap(s -> Mono.justOrEmpty(converter.convert(s, true)));
    }

    @Override
    public Mono<List<DataUnitSchemaServiceModel>> findAll(@NonNull final PaginationParams params) {
        return dao.findAll(params).flatMap(schemas ->
                Mono.just(MassProcessingUtils.processQuietly(schemas, schema -> converter.convert(schema, false))));
    }

    @Override
    public Mono<Void> deleteById(@NonNull final Long id) {
        return dao.deleteById(id);
    }

    @Override
    public Mono<Long> count() {
        return dao.count();
    }
}
