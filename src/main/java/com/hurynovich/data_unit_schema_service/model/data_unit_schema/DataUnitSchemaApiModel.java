package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.data_unit_schema_service.model.ApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;

import java.util.List;

public interface DataUnitSchemaApiModel extends ApiModel<Long> {

    String getName();

    List<DataUnitPropertySchemaApiModel> getPropertySchemas();
}