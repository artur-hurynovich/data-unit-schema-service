package com.hurynovich.data_unit_schema_service.service.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.dao.DataUnitPropertySchemaDao;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.service.AbstractBaseService;
import com.hurynovich.data_unit_schema_service.service.DataUnitPropertySchemaService;
import com.hurynovich.data_unit_schema_service.utils.MassProcessingUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class DataUnitPropertySchemaServiceImpl
        extends AbstractBaseService<DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel, String>
        implements DataUnitPropertySchemaService {

    private final DataUnitPropertySchemaDao dao;

    private final ServiceConverter<DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> converter;

    public DataUnitPropertySchemaServiceImpl(@NonNull final DataUnitPropertySchemaDao dao,
                                             @NonNull final ServiceConverter<DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> converter) {
        super(dao, converter);

        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public Mono<List<DataUnitPropertySchemaServiceModel>> findAllBySchemaId(@NonNull final String schemaId) {
        return dao.findAllBySchemaId(schemaId)
                .flatMap(schemas -> Mono.just(MassProcessingUtils.processQuietly(schemas, converter::convert)));
    }
}
