package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.GenerateMetamodel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;

import java.util.List;

@GenerateMetamodel
public record DataUnitSchemaApiModelImpl(Long id, String name,
                                         List<DataUnitPropertySchemaApiModel> propertySchemas)
        implements DataUnitSchemaApiModel {

    @Override
    public Long getId() {
        return id();
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public List<DataUnitPropertySchemaApiModel> getPropertySchemas() {
        return propertySchemas();
    }
}
