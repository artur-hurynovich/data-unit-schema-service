package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitPropertySchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaApiModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaServiceModelGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DataUnitPropertySchemaApiConverterTest {

    private final ApiConverter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel> converter =
            new DataUnitPropertySchemaApiConverter();

    private final ModelGenerator<DataUnitPropertySchemaApiModel> apiModelGenerator =
            new DataUnitPropertySchemaApiModelGenerator();

    private final ModelGenerator<DataUnitPropertySchemaServiceModel> serviceModelGenerator =
            new DataUnitPropertySchemaServiceModelGenerator();

    private final ModelAsserter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> asserter =
            new DataUnitPropertySchemaAsserter();

    @Test
    void convertServiceModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitPropertySchemaServiceModel) null));
    }

    @Test
    void convertServiceModelNotNullTest() {
        final DataUnitPropertySchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitPropertySchemaApiModel apiModel = converter.convert(serviceModel);
        asserter.assertEquals(serviceModel, apiModel);
    }

    @Test
    void convertApiModelNullTest() {
        Assertions.assertNull(converter.convert((DataUnitPropertySchemaApiModel) null));
    }

    @Test
    void convertApiModelNotNullTest() {
        final DataUnitPropertySchemaApiModel apiModel = apiModelGenerator.generate();
        final DataUnitPropertySchemaServiceModel serviceModel = converter.convert(apiModel);
        asserter.assertEquals(apiModel, serviceModel);
    }
}
