package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
class DataUnitSchemaServiceConverter extends AbstractConverter
        implements ServiceConverter<DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> {

    public DataUnitSchemaServiceConverter(final ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public DataUnitSchemaPersistentModel convert(@Nullable final DataUnitSchemaServiceModel source) {
        return convert(source, DataUnitSchemaDocument.class);
    }

    @Override
    public DataUnitSchemaServiceModel convert(@Nullable final DataUnitSchemaPersistentModel source) {
        return convert(source, DataUnitSchemaServiceModelImpl.class);
    }
}
