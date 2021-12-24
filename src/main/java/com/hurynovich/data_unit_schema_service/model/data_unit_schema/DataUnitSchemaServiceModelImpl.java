package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;

import java.util.List;

public record DataUnitSchemaServiceModelImpl(Long id, String name,
                                             List<DataUnitPropertySchemaServiceModel> propertySchemas)
        implements DataUnitSchemaServiceModel {

    @Override
    public Long getId() {
        return id();
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public List<DataUnitPropertySchemaServiceModel> getPropertySchemas() {
        return propertySchemas();
    }
}
