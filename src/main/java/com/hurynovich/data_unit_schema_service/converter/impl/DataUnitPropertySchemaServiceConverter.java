package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModelImpl;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class DataUnitPropertySchemaServiceConverter extends AbstractConverter
        implements ServiceConverter<DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> {

    public DataUnitPropertySchemaServiceConverter(final ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public DataUnitPropertySchemaPersistentModel convert(@Nullable final DataUnitPropertySchemaServiceModel source) {
        return convert(source, DataUnitPropertySchemaDocument.class);
    }

    @Override
    public DataUnitPropertySchemaServiceModel convert(@Nullable final DataUnitPropertySchemaPersistentModel source) {
        return convert(source, DataUnitPropertySchemaServiceModelImpl.class);
    }
}
