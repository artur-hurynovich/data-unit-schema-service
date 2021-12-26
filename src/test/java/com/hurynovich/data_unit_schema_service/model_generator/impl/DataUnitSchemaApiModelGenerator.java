package com.hurynovich.data_unit_schema_service.model_generator.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;

import java.util.Arrays;
import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_DATE_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_DATE_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_DATE_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_3;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_2;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_NAME_3;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TIME_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TIME_PROPERTY_SCHEMA_NAME;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TIME_PROPERTY_SCHEMA_TYPE;

public class DataUnitSchemaApiModelGenerator implements ModelGenerator<DataUnitSchemaApiModel> {

    @Override
    public DataUnitSchemaApiModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemasWithNullIds());
    }

    private DataUnitSchemaApiModel processGenerate(final Long id, final String name,
                                                   final List<DataUnitPropertySchemaApiModel> propertySchemas) {
        return new DataUnitSchemaApiModelImpl(id, name, propertySchemas);
    }

    private List<DataUnitPropertySchemaApiModel> generatePropertySchemasWithNullIds() {
        final DataUnitPropertySchemaApiModel propertySchema1 = processGeneratePropertySchema(
                null, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema2 = processGeneratePropertySchema(
                null, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema3 = processGeneratePropertySchema(
                null, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema4 = processGeneratePropertySchema(
                null, DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_NAME, DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema5 = processGeneratePropertySchema(
                null, DATA_UNIT_DATE_PROPERTY_SCHEMA_NAME, DATA_UNIT_DATE_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema6 = processGeneratePropertySchema(
                null, DATA_UNIT_TIME_PROPERTY_SCHEMA_NAME, DATA_UNIT_TIME_PROPERTY_SCHEMA_TYPE);

        return Arrays.asList(propertySchema1, propertySchema2, propertySchema3, propertySchema4,
                propertySchema5, propertySchema6);
    }

    private DataUnitPropertySchemaApiModel processGeneratePropertySchema(final Long id, final String name,
                                                                         final DataUnitPropertyType type) {
        return new DataUnitPropertySchemaApiModelImpl(id, name, type);
    }

    @Override
    public DataUnitSchemaApiModel generate() {
        return processGenerate(DATA_UNIT_SCHEMA_ID_1, DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemas());
    }

    private List<DataUnitPropertySchemaApiModel> generatePropertySchemas() {
        final DataUnitPropertySchemaApiModel propertySchema1 = processGeneratePropertySchema(
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema2 = processGeneratePropertySchema(
                DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema3 = processGeneratePropertySchema(
                DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema4 = processGeneratePropertySchema(
                DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_ID, DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema5 = processGeneratePropertySchema(
                DATA_UNIT_DATE_PROPERTY_SCHEMA_ID, DATA_UNIT_DATE_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_DATE_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaApiModel propertySchema6 = processGeneratePropertySchema(
                DATA_UNIT_TIME_PROPERTY_SCHEMA_ID, DATA_UNIT_TIME_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_TIME_PROPERTY_SCHEMA_TYPE);

        return Arrays.asList(propertySchema1, propertySchema2, propertySchema3, propertySchema4,
                propertySchema5, propertySchema6);
    }

    @Override
    public List<DataUnitSchemaApiModel> generateListWithNullIds() {
        final DataUnitSchemaApiModel schema1 = processGenerate(null,
                DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemasWithNullIds());
        final DataUnitSchemaApiModel schema2 = processGenerate(null,
                DATA_UNIT_SCHEMA_NAME_2, generatePropertySchemasWithNullIds());
        final DataUnitSchemaApiModel schema3 = processGenerate(null,
                DATA_UNIT_SCHEMA_NAME_3, generatePropertySchemasWithNullIds());

        return Arrays.asList(schema1, schema2, schema3);
    }

    @Override
    public List<DataUnitSchemaApiModel> generateList() {
        final DataUnitSchemaApiModel schema1 = processGenerate(DATA_UNIT_SCHEMA_ID_1,
                DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemas());
        final DataUnitSchemaApiModel schema2 = processGenerate(DATA_UNIT_SCHEMA_ID_2,
                DATA_UNIT_SCHEMA_NAME_2, generatePropertySchemas());
        final DataUnitSchemaApiModel schema3 = processGenerate(DATA_UNIT_SCHEMA_ID_3,
                DATA_UNIT_SCHEMA_NAME_3, generatePropertySchemas());

        return Arrays.asList(schema1, schema2, schema3);
    }
}
