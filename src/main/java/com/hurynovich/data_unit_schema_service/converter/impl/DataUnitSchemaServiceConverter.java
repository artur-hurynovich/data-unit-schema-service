package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaEntity;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl;
import com.hurynovich.data_unit_schema_service.utils.MassProcessingUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
public class DataUnitSchemaServiceConverter
        implements ServiceConverter<DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> {

    @Override
    public DataUnitSchemaPersistentModel convert(@Nullable final DataUnitSchemaServiceModel source) {
        final DataUnitSchemaEntity target;
        if (source != null) {
            target = new DataUnitSchemaEntity();
            target.setId(source.getId());
            target.setName(source.getName());
            target.setPropertySchemas(MassProcessingUtils.
                    processQuietly(source.getPropertySchemas(), this::convertPropertySchema));
        } else {
            target = null;
        }

        return target;
    }

    private DataUnitPropertySchemaPersistentModel convertPropertySchema(
            @Nullable final DataUnitPropertySchemaServiceModel source) {
        final DataUnitPropertySchemaEntity target;
        if (source != null) {
            target = new DataUnitPropertySchemaEntity();
            target.setId(source.getId());
            target.setName(source.getName());
            target.setType(source.getType());
        } else {
            target = null;
        }

        return target;
    }

    @Override
    public DataUnitSchemaServiceModel convert(@Nullable final DataUnitSchemaPersistentModel source) {
        final DataUnitSchemaServiceModelImpl target;
        if (source != null) {
            target = new DataUnitSchemaServiceModelImpl(source.getId(), source.getName(),
                    MassProcessingUtils.processQuietly(source.getPropertySchemas(),
                            this::convertPropertySchema));
        } else {
            target = null;
        }

        return target;
    }

    private DataUnitPropertySchemaServiceModel convertPropertySchema(
            @Nullable final DataUnitPropertySchemaPersistentModel source) {
        final DataUnitPropertySchemaServiceModelImpl target;
        if (source != null) {
            target = new DataUnitPropertySchemaServiceModelImpl(source.getId(), source.getName(), source.getType());
        } else {
            target = null;
        }

        return target;
    }
}
