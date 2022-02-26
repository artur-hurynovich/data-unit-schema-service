package com.hurynovich.data_unit_schema_service.model.data_unit_property_schema;

import com.hurynovich.GenerateMetamodel;
import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@GenerateMetamodel
public class DataUnitPropertySchemaDocument implements DataUnitPropertySchemaPersistentModel {

    @Id
    private String id;

    private String name;

    private DataUnitPropertyType type;

    private String schemaId;

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
    public DataUnitPropertyType getType() {
        return type;
    }

    public void setType(final DataUnitPropertyType type) {
        this.type = type;
    }

    @Override
    public String getSchemaId() {
        return schemaId;
    }

    public void setSchemaId(final String schemaId) {
        this.schemaId = schemaId;
    }
}
