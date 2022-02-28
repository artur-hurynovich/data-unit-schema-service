package com.hurynovich.data_unit_schema_service.model_generator.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaDocument;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;

import java.util.Arrays;
import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_3;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_3;

public class DataUnitSchemaPersistentModelGenerator implements ModelGenerator<DataUnitSchemaPersistentModel> {

    @Override
    public DataUnitSchemaPersistentModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_SCHEMA_NAME_1);
    }

    private DataUnitSchemaPersistentModel processGenerate(final String id, final String name) {
        final DataUnitSchemaDocument schema = new DataUnitSchemaDocument();
        schema.setId(id);
        schema.setName(name);

        return schema;
    }

    private DataUnitPropertySchemaPersistentModel processGeneratePropertySchema(final String id, final String name,
                                                                                final DataUnitPropertyType type) {
        final DataUnitPropertySchemaDocument propertySchema = new DataUnitPropertySchemaDocument();
        propertySchema.setId(id);
        propertySchema.setName(name);
        propertySchema.setType(type);

        return propertySchema;
    }

    @Override
    public DataUnitSchemaPersistentModel generate() {
        return processGenerate(DATA_UNIT_SCHEMA_ID_1, DATA_UNIT_SCHEMA_NAME_1);
    }

    @Override
    public List<DataUnitSchemaPersistentModel> generateListWithNullIds() {
        final DataUnitSchemaPersistentModel schema1 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_1);
        final DataUnitSchemaPersistentModel schema2 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_2);
        final DataUnitSchemaPersistentModel schema3 = processGenerate(null, DATA_UNIT_SCHEMA_NAME_3);

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitSchemaPersistentModel> generateList() {
        final DataUnitSchemaPersistentModel schema1 = processGenerate(DATA_UNIT_SCHEMA_ID_1, DATA_UNIT_SCHEMA_NAME_1);
        final DataUnitSchemaPersistentModel schema2 = processGenerate(DATA_UNIT_SCHEMA_ID_2, DATA_UNIT_SCHEMA_NAME_2);
        final DataUnitSchemaPersistentModel schema3 = processGenerate(DATA_UNIT_SCHEMA_ID_3, DATA_UNIT_SCHEMA_NAME_3);

        return Arrays.asList(schema1, schema2, schema3);
    }
}
