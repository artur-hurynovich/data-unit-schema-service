package com.hurynovich.data_unit_schema_service.model.data_unit_property_schema;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.PersistentModel;

public interface DataUnitPropertySchemaPersistentModel extends PersistentModel<Long> {

    String getName();

    DataUnitPropertyType getType();
}
