package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
class DataUnitSchemaServiceConverter
        implements ServiceConverter<DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> {

    @Override
    public DataUnitSchemaPersistentModel convert(@Nullable final DataUnitSchemaServiceModel source) {
        final DataUnitSchemaDocument target;
        if (source != null) {
            target = new DataUnitSchemaDocument();
            target.setId(source.getId());
            target.setName(source.getName());
        } else {
            target = null;
        }

        return target;
    }

    @Override
    public DataUnitSchemaServiceModel convert(@Nullable final DataUnitSchemaPersistentModel source) {
        final DataUnitSchemaServiceModelImpl target;
        if (source != null) {
            target = new DataUnitSchemaServiceModelImpl(source.getId(), source.getName());
        } else {
            target = null;
        }

        return target;
    }
}
