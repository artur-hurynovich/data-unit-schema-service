package com.hurynovich.data_unit_schema_service.model_generator.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;

import java.util.Arrays;
import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_3;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_3;

public class DataUnitSchemaServiceModelGenerator implements ModelGenerator<DataUnitSchemaServiceModel> {

    @Override
    public DataUnitSchemaServiceModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_SCHEMA_NAME_1);
    }

    private DataUnitSchemaServiceModel processGenerate(final String id, final String name) {
        return new DataUnitSchemaServiceModelImpl(id, name);
    }

    @Override
    public DataUnitSchemaServiceModel generate() {
        return processGenerate(DATA_UNIT_SCHEMA_ID_1, DATA_UNIT_SCHEMA_NAME_1);
    }

    @Override
    public List<DataUnitSchemaServiceModel> generateListWithNullIds() {
        final DataUnitSchemaServiceModel schema1 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_1);
        final DataUnitSchemaServiceModel schema2 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_2);
        final DataUnitSchemaServiceModel schema3 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_3);

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitSchemaServiceModel> generateList() {
        final DataUnitSchemaServiceModel schema1 = processGenerate(DATA_UNIT_SCHEMA_ID_1, DATA_UNIT_SCHEMA_NAME_1);
        final DataUnitSchemaServiceModel schema2 = processGenerate(DATA_UNIT_SCHEMA_ID_2, DATA_UNIT_SCHEMA_NAME_2);
        final DataUnitSchemaServiceModel schema3 = processGenerate(DATA_UNIT_SCHEMA_ID_3, DATA_UNIT_SCHEMA_NAME_3);

        return Arrays.asList(schema1, schema2, schema3);
    }
}
