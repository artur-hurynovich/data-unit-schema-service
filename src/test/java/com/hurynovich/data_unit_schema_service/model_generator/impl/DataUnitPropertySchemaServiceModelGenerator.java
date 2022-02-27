package com.hurynovich.data_unit_schema_service.model_generator.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModelImpl;
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

public class DataUnitPropertySchemaServiceModelGenerator
        implements ModelGenerator<DataUnitPropertySchemaServiceModel> {

    @Override
    public DataUnitPropertySchemaServiceModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE,
                DATA_UNIT_SCHEMA_ID_1);
    }

    private DataUnitPropertySchemaServiceModel processGenerate(final String id, final String name,
                                                               final DataUnitPropertyType type,
                                                               final String schemaId) {
        return new DataUnitPropertySchemaServiceModelImpl(id, name, type, schemaId);
    }

    @Override
    public DataUnitPropertySchemaServiceModel generate() {
        return processGenerate(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
    }

    @Override
    public List<DataUnitPropertySchemaServiceModel> generateListWithNullIds() {
        final DataUnitPropertySchemaServiceModel schema1 = processGenerate(null,
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaServiceModel schema2 = processGenerate(null,
                DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaServiceModel schema3 = processGenerate(null,
                DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitPropertySchemaServiceModel> generateList() {
        final DataUnitPropertySchemaServiceModel schema1 = processGenerate(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID,
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaServiceModel schema2 = processGenerate(DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID,
                DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaServiceModel schema3 = processGenerate(DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID,
                DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE, DATA_UNIT_SCHEMA_ID_1);

        return Arrays.asList(schema1, schema2, schema3);
    }
}
