package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitSchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaApiModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaServiceModelGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataUnitSchemaApiConverterTest {

    private final ApiConverter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel> converter =
            new DataUnitSchemaApiConverter();

    private final ModelGenerator<DataUnitSchemaApiModel> apiModelGenerator =
            new DataUnitSchemaApiModelGenerator();

    private final ModelGenerator<DataUnitSchemaServiceModel> serviceModelGenerator =
            new DataUnitSchemaServiceModelGenerator();

    private final ModelAsserter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> asserter =
            new DataUnitSchemaAsserter();

    @Test
    void convertServiceModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitSchemaServiceModel) null));
    }

    @Test
    void convertServiceModelNotNullTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitSchemaApiModel apiModel = converter.convert(serviceModel);
        asserter.assertEquals(serviceModel, apiModel);
    }

    @Test
    void convertApiModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitSchemaApiModel) null));
    }

    @Test
    void convertApiModelNotNullTest() {
        final DataUnitSchemaApiModel apiModel = apiModelGenerator.generate();
        final DataUnitSchemaServiceModel serviceModel = converter.convert(apiModel);
        asserter.assertEquals(apiModel, serviceModel);
    }
}
