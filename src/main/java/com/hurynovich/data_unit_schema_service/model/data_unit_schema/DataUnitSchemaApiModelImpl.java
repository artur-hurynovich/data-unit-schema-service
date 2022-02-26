package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.GenerateMetamodel;

@GenerateMetamodel
public record DataUnitSchemaApiModelImpl(String id, String name)
        implements DataUnitSchemaApiModel {

    @Override
    public String getId() {
        return id();
    }

    @Override
    public String getName() {
        return name();
    }
}
