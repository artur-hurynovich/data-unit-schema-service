package com.hurynovich.data_unit_schema_service.model_asserter.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl_;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import org.junit.jupiter.api.Assertions;

import java.util.Set;

public class DataUnitPropertySchemaAsserter
        implements ModelAsserter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> {

    @Override
    public void assertEquals(final DataUnitPropertySchemaPersistentModel expected,
                             final DataUnitPropertySchemaPersistentModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitPropertySchemaWrapper.of(expected), DataUnitPropertySchemaWrapper.of(actual),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitPropertySchemaServiceModel expected,
                             final DataUnitPropertySchemaServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitPropertySchemaWrapper.of(expected), DataUnitPropertySchemaWrapper.of(actual),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitPropertySchemaApiModel expected,
                             final DataUnitPropertySchemaApiModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitPropertySchemaWrapper.of(expected), DataUnitPropertySchemaWrapper.of(actual),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitPropertySchemaApiModel expected,
                             final DataUnitPropertySchemaServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitPropertySchemaWrapper.of(expected), DataUnitPropertySchemaWrapper.of(actual),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitPropertySchemaServiceModel expected,
                             final DataUnitPropertySchemaApiModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitPropertySchemaWrapper.of(expected), DataUnitPropertySchemaWrapper.of(actual),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitPropertySchemaServiceModel expected,
                             final DataUnitPropertySchemaPersistentModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitPropertySchemaWrapper.of(expected), DataUnitPropertySchemaWrapper.of(actual),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitPropertySchemaPersistentModel expected,
                             final DataUnitPropertySchemaServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitPropertySchemaWrapper.of(expected), DataUnitPropertySchemaWrapper.of(actual),
                ignoreProperties);
    }

    private void processAssertEquals(final DataUnitPropertySchemaWrapper expected,
                                     final DataUnitPropertySchemaWrapper actual,
                                     final String... ignoreProperties) {
        final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
        if (!ignorePropertiesSet.contains(DataUnitPropertySchemaApiModelImpl_.ID)) {
            Assertions.assertEquals(expected.id(), actual.id());
        }

        if (!ignorePropertiesSet.contains(DataUnitPropertySchemaApiModelImpl_.NAME)) {
            Assertions.assertEquals(expected.name(), actual.name());
        }

        if (!ignorePropertiesSet.contains(DataUnitPropertySchemaApiModelImpl_.TYPE)) {
            Assertions.assertEquals(expected.type(), actual.type());
        }

        if (!ignorePropertiesSet.contains(DataUnitPropertySchemaApiModelImpl_.SCHEMA_ID)) {
            Assertions.assertEquals(expected.schemaId(), actual.schemaId());
        }
    }

    private record DataUnitPropertySchemaWrapper(String id, String name, DataUnitPropertyType type, String schemaId) {

        public static DataUnitPropertySchemaWrapper of(final DataUnitPropertySchemaApiModel schema) {
            return new DataUnitPropertySchemaWrapper(schema.getId(), schema.getName(), schema.getType(),
                    schema.getSchemaId());
        }

        public static DataUnitPropertySchemaWrapper of(final DataUnitPropertySchemaServiceModel schema) {
            return new DataUnitPropertySchemaWrapper(schema.getId(), schema.getName(), schema.getType(),
                    schema.getSchemaId());
        }

        public static DataUnitPropertySchemaWrapper of(final DataUnitPropertySchemaPersistentModel schema) {
            return new DataUnitPropertySchemaWrapper(schema.getId(), schema.getName(), schema.getType(),
                    schema.getSchemaId());
        }
    }
}
