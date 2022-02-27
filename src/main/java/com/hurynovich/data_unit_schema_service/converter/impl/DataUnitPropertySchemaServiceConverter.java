package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModelImpl;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class DataUnitPropertySchemaServiceConverter
        implements ServiceConverter<DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> {

    @Override
    public DataUnitPropertySchemaPersistentModel convert(@Nullable final DataUnitPropertySchemaServiceModel source) {
        final DataUnitPropertySchemaDocument target;
        if (source != null) {
            target = new DataUnitPropertySchemaDocument();
            target.setId(source.getId());
            target.setName(source.getName());
            target.setType(source.getType());
            target.setSchemaId(source.getSchemaId());
        } else {
            target = null;
        }

        return target;
    }

    @Override
    public DataUnitPropertySchemaServiceModel convert(@Nullable final DataUnitPropertySchemaPersistentModel source) {
        final DataUnitPropertySchemaServiceModelImpl target;
        if (source != null) {
            target = new DataUnitPropertySchemaServiceModelImpl(source.getId(), source.getName(), source.getType(),
                    source.getSchemaId());
        } else {
            target = null;
        }

        return target;
    }
}
