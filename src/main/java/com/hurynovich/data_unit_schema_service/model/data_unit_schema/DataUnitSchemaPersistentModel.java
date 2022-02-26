package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.data_unit_schema_service.model.PersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;

import java.util.List;

public interface DataUnitSchemaPersistentModel extends PersistentModel<String> {

    String getName();

    List<DataUnitPropertySchemaPersistentModel> getPropertySchemas();
}
