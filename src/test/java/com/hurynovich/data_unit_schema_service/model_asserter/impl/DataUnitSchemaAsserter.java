package com.hurynovich.data_unit_schema_service.model_asserter.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaDocument_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import org.junit.jupiter.api.Assertions;

import java.util.Set;

public class DataUnitSchemaAsserter implements ModelAsserter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> {

    @Override
    public void assertEquals(final DataUnitSchemaPersistentModel expected,
                             final DataUnitSchemaPersistentModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitSchemaWrapper.of(expected, ignoreProperties),
                DataUnitSchemaWrapper.of(actual, ignoreProperties),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitSchemaServiceModel expected,
                             final DataUnitSchemaServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitSchemaWrapper.of(expected, ignoreProperties),
                DataUnitSchemaWrapper.of(actual, ignoreProperties),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitSchemaApiModel expected,
                             final DataUnitSchemaApiModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitSchemaWrapper.of(expected, ignoreProperties),
                DataUnitSchemaWrapper.of(actual, ignoreProperties),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitSchemaApiModel expected,
                             final DataUnitSchemaServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitSchemaWrapper.of(expected, ignoreProperties),
                DataUnitSchemaWrapper.of(actual, ignoreProperties),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitSchemaServiceModel expected,
                             final DataUnitSchemaApiModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitSchemaWrapper.of(expected, ignoreProperties),
                DataUnitSchemaWrapper.of(actual, ignoreProperties),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitSchemaServiceModel expected,
                             final DataUnitSchemaPersistentModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitSchemaWrapper.of(expected, ignoreProperties),
                DataUnitSchemaWrapper.of(actual, ignoreProperties),
                ignoreProperties);
    }

    @Override
    public void assertEquals(final DataUnitSchemaPersistentModel expected,
                             final DataUnitSchemaServiceModel actual,
                             final String... ignoreProperties) {
        processAssertEquals(DataUnitSchemaWrapper.of(expected, ignoreProperties),
                DataUnitSchemaWrapper.of(actual, ignoreProperties),
                ignoreProperties);
    }

    private void processAssertEquals(final DataUnitSchemaWrapper expected,
                                     final DataUnitSchemaWrapper actual,
                                     final String... ignoreProperties) {
        final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
        if (!ignorePropertiesSet.contains(DataUnitSchemaDocument_.ID)) {
            Assertions.assertEquals(expected.id(), actual.id());
        }

        if (!ignorePropertiesSet.contains(DataUnitSchemaDocument_.NAME)) {
            Assertions.assertEquals(expected.name(), actual.name());
        }
    }

    private record DataUnitSchemaWrapper(String id, String name) {

        public static DataUnitSchemaWrapper of(final DataUnitSchemaApiModel schema,
                                               final String... ignoreProperties) {
            final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
            final String id;
            if (!ignorePropertiesSet.contains(DataUnitSchemaDocument_.ID)) {
                id = schema.getId();
            } else {
                id = null;
            }

            final String name;
            if (!ignorePropertiesSet.contains(DataUnitSchemaDocument_.NAME)) {
                name = schema.getName();
            } else {
                name = null;
            }

            return new DataUnitSchemaWrapper(id, name);
        }

        public static DataUnitSchemaWrapper of(final DataUnitSchemaServiceModel schema,
                                               final String... ignoreProperties) {
            final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
            final String id;
            if (!ignorePropertiesSet.contains(DataUnitSchemaDocument_.ID)) {
                id = schema.getId();
            } else {
                id = null;
            }

            final String name;
            if (!ignorePropertiesSet.contains(DataUnitSchemaDocument_.NAME)) {
                name = schema.getName();
            } else {
                name = null;
            }

            return new DataUnitSchemaWrapper(id, name);
        }

        public static DataUnitSchemaWrapper of(final DataUnitSchemaPersistentModel schema,
                                               final String... ignoreProperties) {
            final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
            final String id;
            if (!ignorePropertiesSet.contains(DataUnitSchemaDocument_.ID)) {
                id = schema.getId();
            } else {
                id = null;
            }

            final String name;
            if (!ignorePropertiesSet.contains(DataUnitSchemaDocument_.NAME)) {
                name = schema.getName();
            } else {
                name = null;
            }

            return new DataUnitSchemaWrapper(id, name);
        }
    }
}
