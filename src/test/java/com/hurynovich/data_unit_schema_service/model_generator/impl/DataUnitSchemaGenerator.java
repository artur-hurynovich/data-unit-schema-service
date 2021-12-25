package com.hurynovich.data_unit_schema_service.model_generator.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaEntity;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;

import java.util.Arrays;
import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_DATE_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_DATE_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_3;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_3;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TIME_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TIME_PROPERTY_SCHEMA_TYPE;

public class DataUnitSchemaGenerator implements ModelGenerator<DataUnitSchemaPersistentModel> {

    @Override
    public DataUnitSchemaPersistentModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemasWithNullIds());
    }

    private DataUnitSchemaPersistentModel processGenerate(final Long id, final String name,
                                                          final List<DataUnitPropertySchemaPersistentModel> propertySchemas) {
        final DataUnitSchemaEntity schema = new DataUnitSchemaEntity();
        schema.setId(id);
        schema.setName(name);
        schema.setPropertySchemas(propertySchemas);

        return schema;
    }

    private List<DataUnitPropertySchemaPersistentModel> generatePropertySchemasWithNullIds() {
        final DataUnitPropertySchemaPersistentModel propertySchema1 = processGeneratePropertySchema(
                null, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaPersistentModel propertySchema2 = processGeneratePropertySchema(
                null, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaPersistentModel propertySchema3 = processGeneratePropertySchema(
                null, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaPersistentModel propertySchema4 = processGeneratePropertySchema(
                null, DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_NAME, DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaPersistentModel propertySchema5 = processGeneratePropertySchema(
                null, DATA_UNIT_DATE_PROPERTY_SCHEMA_NAME, DATA_UNIT_DATE_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaPersistentModel propertySchema6 = processGeneratePropertySchema(
                null, DATA_UNIT_TIME_PROPERTY_SCHEMA_NAME, DATA_UNIT_TIME_PROPERTY_SCHEMA_TYPE);

        return Arrays.asList(propertySchema1, propertySchema2, propertySchema3, propertySchema4,
                propertySchema5, propertySchema6);
    }

    private DataUnitPropertySchemaPersistentModel processGeneratePropertySchema(final Long id, final String name,
                                                                                final DataUnitPropertyType type) {
        final DataUnitPropertySchemaEntity propertySchema = new DataUnitPropertySchemaEntity();
        propertySchema.setId(id);
        propertySchema.setName(name);
        propertySchema.setType(type);

        return propertySchema;
    }

    @Override
    public List<DataUnitSchemaPersistentModel> generateListWithNullIds() {
        final DataUnitSchemaPersistentModel schema1 = processGenerate(DATA_UNIT_SCHEMA_ID_1,
                DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemasWithNullIds());
        final DataUnitSchemaPersistentModel schema2 = processGenerate(DATA_UNIT_SCHEMA_ID_2,
                DATA_UNIT_SCHEMA_NAME_2, generatePropertySchemasWithNullIds());
        final DataUnitSchemaPersistentModel schema3 = processGenerate(DATA_UNIT_SCHEMA_ID_3,
                DATA_UNIT_SCHEMA_NAME_3, generatePropertySchemasWithNullIds());

        return Arrays.asList(schema1, schema2, schema3);
    }
}
