package com.hurynovich.data_unit_schema_service.model_asserter.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import org.junit.jupiter.api.Assertions;

import java.util.List;
import java.util.Set;

public class DataUnitSchemaAsserter implements ModelAsserter<DataUnitSchemaPersistentModel> {

    @Override
    public void assertEquals(final DataUnitSchemaPersistentModel expected,
                             final DataUnitSchemaPersistentModel actual,
                             final String... ignoreProperties) {
        final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
        if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.ID)) {
            Assertions.assertEquals(expected.getId(), actual.getId());
        }

        if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.NAME)) {
            Assertions.assertEquals(expected.getName(), actual.getName());
        }

        if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.PROPERTY_SCHEMAS)) {
            final List<DataUnitPropertySchemaPersistentModel> expectedPropertySchemas = expected.getPropertySchemas();
            final List<DataUnitPropertySchemaPersistentModel> actualPropertySchemas = actual.getPropertySchemas();
            Assertions.assertEquals(expectedPropertySchemas.size(), actualPropertySchemas.size());

            for (int i = 0; i < expectedPropertySchemas.size(); i++) {
                final DataUnitPropertySchemaPersistentModel expectedPropertySchema = expectedPropertySchemas.get(i);
                final DataUnitPropertySchemaPersistentModel actualPropertySchema = actualPropertySchemas.get(i);
                if (!ignorePropertiesSet.contains("id")) {
                    Assertions.assertEquals(expectedPropertySchema.getId(), actualPropertySchema.getId());
                }

                Assertions.assertEquals(expectedPropertySchema.getName(), actualPropertySchema.getName());
                Assertions.assertEquals(expectedPropertySchema.getType(), actualPropertySchema.getType());
            }
        }
    }
}
