package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.GenerateMetamodel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@GenerateMetamodel
public class DataUnitSchemaDocument implements DataUnitSchemaPersistentModel {

    @Id
    private String id;

    private String name;

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
}
