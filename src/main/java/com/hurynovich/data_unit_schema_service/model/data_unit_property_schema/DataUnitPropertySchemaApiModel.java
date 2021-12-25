package com.hurynovich.data_unit_schema_service.model.data_unit_property_schema;

import com.hurynovich.data_unit_schema_service.model.ApiModel;
import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;

public interface DataUnitPropertySchemaApiModel extends ApiModel<Long> {

    String getName();

    DataUnitPropertyType getType();
}