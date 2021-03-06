package com.hurynovich.data_unit_schema_service.model.data_unit_schema;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.hurynovich.data_unit_schema_service.model.ApiModel;

@JsonDeserialize(as = DataUnitSchemaApiModelImpl.class)
public interface DataUnitSchemaApiModel extends ApiModel<String> {

    String getName();
}
