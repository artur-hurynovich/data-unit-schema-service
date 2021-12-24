package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.hurynovich.data_unit_schema_service.model.ServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;

import java.util.List;

public interface DataUnitSchemaServiceModel extends ServiceModel<Long> {

    String getName();

    List<DataUnitPropertySchemaServiceModel> getPropertySchemas();
}
