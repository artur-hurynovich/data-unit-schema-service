package com.hurynovich.data_unit_schema_service.model.data_unit_property_schema;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.ServiceModel;

public interface DataUnitPropertySchemaServiceModel extends ServiceModel<String> {

    String getName();

    DataUnitPropertyType getType();
}
