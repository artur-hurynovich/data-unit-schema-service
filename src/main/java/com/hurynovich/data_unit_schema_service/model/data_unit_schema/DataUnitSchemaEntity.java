package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaEntity;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "data_unit_schema")
public class DataUnitSchemaEntity implements DataUnitSchemaPersistentModel {

    @Id
    @GeneratedValue(generator = "data_unit_schema_id_generator")
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            orphanRemoval = true,
            targetEntity = DataUnitPropertySchemaEntity.class)
    @JoinColumn(name = "data_unit_schema_id", nullable = false)
    private List<DataUnitPropertySchemaPersistentModel> propertySchemas;

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
    public List<DataUnitPropertySchemaPersistentModel> getPropertySchemas() {
        return propertySchemas;
    }

    public void setPropertySchemas(final List<DataUnitPropertySchemaPersistentModel> propertySchemas) {
        this.propertySchemas = propertySchemas;
    }
}
