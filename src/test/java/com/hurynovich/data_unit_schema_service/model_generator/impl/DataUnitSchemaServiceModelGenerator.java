package com.hurynovich.data_unit_schema_service.model_generator.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl;
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

public class DataUnitSchemaServiceModelGenerator implements ModelGenerator<DataUnitSchemaServiceModel> {

    @Override
    public DataUnitSchemaServiceModel generateWithNullId() {
        return processGenerate(null, DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemasWithNullIds());
    }

    private DataUnitSchemaServiceModel processGenerate(final Long id, final String name,
                                                       final List<DataUnitPropertySchemaServiceModel> propertySchemas) {
        return new DataUnitSchemaServiceModelImpl(id, name, propertySchemas);
    }

    private List<DataUnitPropertySchemaServiceModel> generatePropertySchemasWithNullIds() {
        final DataUnitPropertySchemaServiceModel propertySchema1 = processGeneratePropertySchema(
                null, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema2 = processGeneratePropertySchema(
                null, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema3 = processGeneratePropertySchema(
                null, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema4 = processGeneratePropertySchema(
                null, DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_NAME, DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema5 = processGeneratePropertySchema(
                null, DATA_UNIT_DATE_PROPERTY_SCHEMA_NAME, DATA_UNIT_DATE_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema6 = processGeneratePropertySchema(
                null, DATA_UNIT_TIME_PROPERTY_SCHEMA_NAME, DATA_UNIT_TIME_PROPERTY_SCHEMA_TYPE);

        return Arrays.asList(propertySchema1, propertySchema2, propertySchema3, propertySchema4,
                propertySchema5, propertySchema6);
    }

    private DataUnitPropertySchemaServiceModel processGeneratePropertySchema(final Long id, final String name,
                                                                             final DataUnitPropertyType type) {
        return new DataUnitPropertySchemaServiceModelImpl(id, name, type);
    }

    @Override
    public DataUnitSchemaServiceModel generate() {
        return processGenerate(DATA_UNIT_SCHEMA_ID_1, DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemas());
    }

    private List<DataUnitPropertySchemaServiceModel> generatePropertySchemas() {
        final DataUnitPropertySchemaServiceModel propertySchema1 = processGeneratePropertySchema(
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_TEXT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema2 = processGeneratePropertySchema(
                DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID, DATA_UNIT_INTEGER_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_INTEGER_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema3 = processGeneratePropertySchema(
                DATA_UNIT_FLOAT_PROPERTY_SCHEMA_ID, DATA_UNIT_FLOAT_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_FLOAT_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema4 = processGeneratePropertySchema(
                DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_ID, DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_BOOLEAN_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema5 = processGeneratePropertySchema(
                DATA_UNIT_DATE_PROPERTY_SCHEMA_ID, DATA_UNIT_DATE_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_DATE_PROPERTY_SCHEMA_TYPE);
        final DataUnitPropertySchemaServiceModel propertySchema6 = processGeneratePropertySchema(
                DATA_UNIT_TIME_PROPERTY_SCHEMA_ID, DATA_UNIT_TIME_PROPERTY_SCHEMA_NAME,
                DATA_UNIT_TIME_PROPERTY_SCHEMA_TYPE);

        return Arrays.asList(propertySchema1, propertySchema2, propertySchema3, propertySchema4,
                propertySchema5, propertySchema6);
    }

    @Override
    public List<DataUnitSchemaServiceModel> generateListWithNullIds() {
        final DataUnitSchemaServiceModel schema1 = processGenerate(DATA_UNIT_SCHEMA_ID_1,
                DATA_UNIT_SCHEMA_NAME_1, generatePropertySchemasWithNullIds());
        final DataUnitSchemaServiceModel schema2 = processGenerate(DATA_UNIT_SCHEMA_ID_2,
                DATA_UNIT_SCHEMA_NAME_2, generatePropertySchemasWithNullIds());
        final DataUnitSchemaServiceModel schema3 = processGenerate(DATA_UNIT_SCHEMA_ID_3,
                DATA_UNIT_SCHEMA_NAME_3, generatePropertySchemasWithNullIds());

        return Arrays.asList(schema1, schema2, schema3);
    }
}
