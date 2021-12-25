package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitSchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaPersistentModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaServiceModelGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DataUnitSchemaServiceConverterTest {

    private final ServiceConverter<DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> converter =
            new DataUnitSchemaServiceConverter();

    private final ModelGenerator<DataUnitSchemaServiceModel> serviceModelGenerator =
            new DataUnitSchemaServiceModelGenerator();

    private final ModelGenerator<DataUnitSchemaPersistentModel> persistentModelGenerator =
            new DataUnitSchemaPersistentModelGenerator();

    private final ModelAsserter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> asserter =
            new DataUnitSchemaAsserter();

    @Test
    void convertPersistentModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitSchemaPersistentModel) null));
    }

    @Test
    void convertPersistentModelNotNullTest() {
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generate();
        final DataUnitSchemaServiceModel serviceModel = converter.convert(persistentModel);
        asserter.assertEquals(persistentModel, serviceModel);
    }

    @Test
    void convertServiceModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitSchemaServiceModel) null));
    }

    @Test
    void convertServiceModelNotNullTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitSchemaPersistentModel persistentModel = converter.convert(serviceModel);
        asserter.assertEquals(serviceModel, persistentModel);
    }
}
