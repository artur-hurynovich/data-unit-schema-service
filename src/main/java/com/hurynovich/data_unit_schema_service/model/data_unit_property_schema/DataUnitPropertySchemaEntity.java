package com.hurynovich.data_unit_schema_service.model.data_unit_property_schema;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "data_unit_property_schema")
public class DataUnitPropertySchemaEntity implements DataUnitPropertySchemaPersistentModel {

    @Id
    @GeneratedValue(generator = "data_unit_property_schema_id_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private DataUnitPropertyType type;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
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
}
