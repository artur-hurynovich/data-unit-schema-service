package com.hurynovich.data_unit_schema_service.converter.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl_;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitSchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaPersistentModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaServiceModelGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class DataUnitSchemaServiceConverterTest {

    private final ServiceConverter<DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> converter =
            new DataUnitSchemaServiceConverter();

    private final ModelGenerator<DataUnitSchemaServiceModel> serviceModelGenerator =
            new DataUnitSchemaServiceModelGenerator();

    private final ModelGenerator<DataUnitSchemaPersistentModel> persistentModelGenerator =
            new DataUnitSchemaPersistentModelGenerator();

    private final ModelAsserter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> asserter =
            new DataUnitSchemaAsserter();

    @Test
    void convertPersistentModelNullConvertAssociationTrueTest() {
        Assertions.assertNull(converter.convert(null, true));
    }

    @Test
    void convertPersistentModelNullConvertAssociationFalseTest() {
        Assertions.assertNull(converter.convert(null, false));
    }

    @Test
    void convertPersistentModelNotNullConvertAssociationTrueTest() {
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generate();
        final DataUnitSchemaServiceModel serviceModel = converter.convert(persistentModel, true);
        asserter.assertEquals(persistentModel, serviceModel);
    }

    @Test
    void convertPersistentModelNotNullConvertAssociationFalseTest() {
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generate();
        final DataUnitSchemaServiceModel serviceModel = converter.convert(persistentModel, false);
        asserter.assertEquals(persistentModel, serviceModel, DataUnitSchemaServiceModelImpl_.PROPERTY_SCHEMAS);

        final List<DataUnitPropertySchemaServiceModel> propertySchemas = serviceModel.getPropertySchemas();
        Assertions.assertNotNull(propertySchemas);
        Assertions.assertTrue(propertySchemas.isEmpty());
    }

    @Test
    void convertServiceModelNullTest() {
        Assertions.assertNull(converter.convert(null));
    }

    @Test
    void convertServiceModelNotNullTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitSchemaPersistentModel persistentModel = converter.convert(serviceModel);
        asserter.assertEquals(serviceModel, persistentModel);
    }
}
