package com.hurynovich.data_unit_schema_service.model_generator.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;

import java.util.Arrays;
import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_3;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_3;

public class DataUnitSchemaApiModelGenerator implements ModelGenerator<DataUnitSchemaApiModel> {

    @Override
    public DataUnitSchemaApiModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_SCHEMA_NAME_1);
    }

    private DataUnitSchemaApiModel processGenerate(final String id, final String name) {
        return new DataUnitSchemaApiModelImpl(id, name);
    }

    @Override
    public DataUnitSchemaApiModel generate() {
        return processGenerate(DATA_UNIT_SCHEMA_ID_1, DATA_UNIT_SCHEMA_NAME_1);
    }

    @Override
    public List<DataUnitSchemaApiModel> generateListWithNullIds() {
        final DataUnitSchemaApiModel schema1 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_1);
        final DataUnitSchemaApiModel schema2 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_2);
        final DataUnitSchemaApiModel schema3 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_3);

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitSchemaApiModel> generateList() {
        final DataUnitSchemaApiModel schema1 = processGenerate(DATA_UNIT_SCHEMA_ID_1, DATA_UNIT_SCHEMA_NAME_1);
        final DataUnitSchemaApiModel schema2 = processGenerate(DATA_UNIT_SCHEMA_ID_2, DATA_UNIT_SCHEMA_NAME_2);
        final DataUnitSchemaApiModel schema3 = processGenerate(DATA_UNIT_SCHEMA_ID_3, DATA_UNIT_SCHEMA_NAME_3);

        return Arrays.asList(schema1, schema2, schema3);
    }
}
