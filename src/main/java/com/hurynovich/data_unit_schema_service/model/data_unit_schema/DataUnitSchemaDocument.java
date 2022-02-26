package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.GenerateMetamodel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@GenerateMetamodel
public class DataUnitSchemaDocument implements DataUnitSchemaPersistentModel {

    @Id
    private String id;

    private String name;

    private List<DataUnitPropertySchemaPersistentModel> propertySchemas;

    @Override
    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public List<DataUnitPropertySchemaPersistentModel> getPropertySchemas() {
        return propertySchemas;
    }

    public void setPropertySchemas(final List<DataUnitPropertySchemaPersistentModel> propertySchemas) {
        this.propertySchemas = propertySchemas;
    }
}
