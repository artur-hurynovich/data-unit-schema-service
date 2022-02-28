package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitPropertySchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaPersistentModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaServiceModelGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataUnitPropertySchemaServiceConverterTest {

    private final ServiceConverter<DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> converter =
            new DataUnitPropertySchemaServiceConverter();

    private final ModelGenerator<DataUnitPropertySchemaServiceModel> serviceModelGenerator =
            new DataUnitPropertySchemaServiceModelGenerator();

    private final ModelGenerator<DataUnitPropertySchemaPersistentModel> persistentModelGenerator =
            new DataUnitPropertySchemaPersistentModelGenerator();

    private final ModelAsserter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> asserter =
            new DataUnitPropertySchemaAsserter();

    @Test
    void convertPersistentModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitPropertySchemaPersistentModel) null));
    }

    @Test
    void convertPersistentModelNotNullTest() {
        final DataUnitPropertySchemaPersistentModel persistentModel = persistentModelGenerator.generate();
        final DataUnitPropertySchemaServiceModel serviceModel = converter.convert(persistentModel);
        asserter.assertEquals(persistentModel, serviceModel);
    }

    @Test
    void convertServiceModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitPropertySchemaServiceModel) null));
    }

    @Test
    void convertServiceModelNotNullTest() {
        final DataUnitPropertySchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitPropertySchemaPersistentModel persistentModel = converter.convert(serviceModel);
        asserter.assertEquals(serviceModel, persistentModel);
    }
}
