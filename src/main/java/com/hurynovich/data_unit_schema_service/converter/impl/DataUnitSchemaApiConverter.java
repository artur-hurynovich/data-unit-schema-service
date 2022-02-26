package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl;
import org.modelmapper.ModelMapper;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
class DataUnitSchemaApiConverter extends AbstractConverter
        implements ApiConverter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel> {

    public DataUnitSchemaApiConverter(final ModelMapper mapper) {
        super(mapper);
    }

    @Override
    public DataUnitSchemaServiceModel convert(@Nullable final DataUnitSchemaApiModel source) {
        return convert(source, DataUnitSchemaServiceModelImpl.class);
    }

    @Override
    public DataUnitSchemaApiModel convert(@Nullable final DataUnitSchemaServiceModel source) {
        return convert(source, DataUnitSchemaApiModelImpl.class);
    }
}
