package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl;
import com.hurynovich.data_unit_schema_service.utils.MassProcessingUtils;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

@Service
class DataUnitSchemaApiConverter implements ApiConverter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel> {

    @Override
    public DataUnitSchemaServiceModel convert(@Nullable final DataUnitSchemaApiModel source) {
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
            @Nullable final DataUnitPropertySchemaApiModel source) {
        final DataUnitPropertySchemaServiceModelImpl target;
        if (source != null) {
            target = new DataUnitPropertySchemaServiceModelImpl(source.getId(), source.getName(), source.getType());
        } else {
            target = null;
        }

        return target;
    }

    @Override
    public DataUnitSchemaApiModel convert(@Nullable final DataUnitSchemaServiceModel source) {
        final DataUnitSchemaApiModelImpl target;
        if (source != null) {
            target = new DataUnitSchemaApiModelImpl(source.getId(), source.getName(),
                    MassProcessingUtils.processQuietly(source.getPropertySchemas(),
                            this::convertPropertySchema));
        } else {
            target = null;
        }

        return target;
    }

    private DataUnitPropertySchemaApiModel convertPropertySchema(
            @Nullable final DataUnitPropertySchemaServiceModel source) {
        final DataUnitPropertySchemaApiModelImpl target;
        if (source != null) {
            target = new DataUnitPropertySchemaApiModelImpl(source.getId(), source.getName(), source.getType());
        } else {
            target = null;
        }

        return target;
    }
}
