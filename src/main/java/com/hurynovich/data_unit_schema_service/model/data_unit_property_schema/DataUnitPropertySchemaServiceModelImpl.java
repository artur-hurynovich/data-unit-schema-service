package com.hurynovich.data_unit_schema_service.model.data_unit_property_schema;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;

public record DataUnitPropertySchemaServiceModelImpl(Long id, String name, DataUnitPropertyType type)
        implements DataUnitPropertySchemaServiceModel {

    @Override
    public Long getId() {
        return id();
    }

    @Override
    public String getName() {
        return name();
    }

    @Override
    public DataUnitPropertyType getType() {
        return type();
    }
}