package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModelImpl;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class DataUnitPropertySchemaApiConverter
        implements ApiConverter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel> {

    @Override
    public DataUnitPropertySchemaServiceModel convert(@Nullable final DataUnitPropertySchemaApiModel source) {
        final DataUnitPropertySchemaServiceModelImpl target;
        if (source != null) {
            target = new DataUnitPropertySchemaServiceModelImpl(source.getId(), source.getName(), source.getType(),
                    source.getSchemaId());
        } else {
            target = null;
        }

        return target;
    }

    @Override
    public DataUnitPropertySchemaApiModel convert(@Nullable final DataUnitPropertySchemaServiceModel source) {
        final DataUnitPropertySchemaApiModelImpl target;
        if (source != null) {
            target = new DataUnitPropertySchemaApiModelImpl(source.getId(), source.getName(), source.getType(),
                    source.getSchemaId());
        } else {
            target = null;
        }

        return target;
    }
}
