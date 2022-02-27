package com.hurynovich.data_unit_schema_service.model_generator.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;

import java.util.Arrays;
import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE;

public class DataUnitPropertySchemaApiModelGenerator implements ModelGenerator<DataUnitPropertySchemaApiModel> {

    @Override
    public DataUnitPropertySchemaApiModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE,
                DATA_UNIT_SCHEMA_ID_1);
    }

    private DataUnitPropertySchemaApiModel processGenerate(final String id, final String name,
                                                           final DataUnitPropertyType type,
                                                           final String schemaId) {
        return new DataUnitPropertySchemaApiModelImpl(id, name, type, schemaId);
    }

    @Override
    public DataUnitPropertySchemaApiModel generate() {
        return processGenerate(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
    }

    @Override
    public List<DataUnitPropertySchemaApiModel> generateListWithNullIds() {
        final DataUnitPropertySchemaApiModel schema1 = processGenerate(null,
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaApiModel schema2 = processGenerate(null,
                DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaApiModel schema3 = processGenerate(null,
                DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitPropertySchemaApiModel> generateList() {
        final DataUnitPropertySchemaApiModel schema1 = processGenerate(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID,
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaApiModel schema2 = processGenerate(DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID,
                DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaApiModel schema3 = processGenerate(DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID,
                DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);

        return Arrays.asList(schema1, schema2, schema3);
    }
}
