package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.GenerateMetamodel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;

import java.util.List;

@GenerateMetamodel
public record DataUnitSchemaServiceModelImpl(String id, String name,
                                             List<DataUnitPropertySchemaServiceModel> propertySchemas)
        implements DataUnitSchemaServiceModel {

    @Override
    public String getId() {
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
