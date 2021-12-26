package com.hurynovich.data_unit_schema_service.model_asserter.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaEntity_;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.utils.MassProcessingUtils;
import org.junit.jupiter.api.Assertions;

import java.util.List;
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
        if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.ID)) {
            Assertions.assertEquals(expected.id(), actual.id());
        }

        if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.NAME)) {
            Assertions.assertEquals(expected.name(), actual.name());
        }

        if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.PROPERTY_SCHEMAS)) {
            final List<DataUnitPropertySchemaWrapper> expectedPropertySchemas = expected.propertySchemas();
            final List<DataUnitPropertySchemaWrapper> actualPropertySchemas = actual.propertySchemas();
            Assertions.assertEquals(expectedPropertySchemas.size(), actualPropertySchemas.size());

            for (int i = 0; i < expectedPropertySchemas.size(); i++) {
                final DataUnitPropertySchemaWrapper expectedPropertySchema = expectedPropertySchemas.get(i);
                final DataUnitPropertySchemaWrapper actualPropertySchema = actualPropertySchemas.get(i);
                if (!ignorePropertiesSet.contains(DataUnitPropertySchemaEntity_.ID)) {
                    Assertions.assertEquals(expectedPropertySchema.id(), actualPropertySchema.id());
                }

                Assertions.assertEquals(expectedPropertySchema.name(), actualPropertySchema.name());
                Assertions.assertEquals(expectedPropertySchema.type(), actualPropertySchema.type());
            }
        }
    }

    private record DataUnitSchemaWrapper(Long id, String name,
                                         List<DataUnitPropertySchemaWrapper> propertySchemas) {

        public static DataUnitSchemaWrapper of(final DataUnitSchemaApiModel schema,
                                               final String... ignoreProperties) {
            final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
            final Long id;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.ID)) {
                id = schema.getId();
            } else {
                id = null;
            }

            final String name;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.NAME)) {
                name = schema.getName();
            } else {
                name = null;
            }

            final List<DataUnitPropertySchemaWrapper> propertySchemas;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.PROPERTY_SCHEMAS)) {
                propertySchemas = MassProcessingUtils.processQuietly(schema.getPropertySchemas(),
                        DataUnitPropertySchemaWrapper::of);
            } else {
                propertySchemas = List.of();
            }

            return new DataUnitSchemaWrapper(id, name, propertySchemas);
        }

        public static DataUnitSchemaWrapper of(final DataUnitSchemaServiceModel schema,
                                               final String... ignoreProperties) {
            final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
            final Long id;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.ID)) {
                id = schema.getId();
            } else {
                id = null;
            }

            final String name;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.NAME)) {
                name = schema.getName();
            } else {
                name = null;
            }

            final List<DataUnitPropertySchemaWrapper> propertySchemas;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.PROPERTY_SCHEMAS)) {
                propertySchemas = MassProcessingUtils.processQuietly(schema.getPropertySchemas(),
                        DataUnitPropertySchemaWrapper::of);
            } else {
                propertySchemas = List.of();
            }

            return new DataUnitSchemaWrapper(id, name, propertySchemas);
        }

        public static DataUnitSchemaWrapper of(final DataUnitSchemaPersistentModel schema,
                                               final String... ignoreProperties) {
            final Set<String> ignorePropertiesSet = Set.of(ignoreProperties);
            final Long id;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.ID)) {
                id = schema.getId();
            } else {
                id = null;
            }

            final String name;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.NAME)) {
                name = schema.getName();
            } else {
                name = null;
            }

            final List<DataUnitPropertySchemaWrapper> propertySchemas;
            if (!ignorePropertiesSet.contains(DataUnitSchemaEntity_.PROPERTY_SCHEMAS)) {
                propertySchemas = MassProcessingUtils.processQuietly(schema.getPropertySchemas(),
                        DataUnitPropertySchemaWrapper::of);
            } else {
                propertySchemas = List.of();
            }

            return new DataUnitSchemaWrapper(id, name, propertySchemas);
        }
    }

    private record DataUnitPropertySchemaWrapper(Long id, String name,
                                                 DataUnitPropertyType type) {

        public static DataUnitPropertySchemaWrapper of(final DataUnitPropertySchemaApiModel propertySchema,
                                                       final String... ignoreProperties) {
            return new DataUnitPropertySchemaWrapper(propertySchema.getId(), propertySchema.getName(),
                    propertySchema.getType());
        }

        public static DataUnitPropertySchemaWrapper of(final DataUnitPropertySchemaServiceModel propertySchema,
                                                       final String... ignoreProperties) {
            return new DataUnitPropertySchemaWrapper(propertySchema.getId(), propertySchema.getName(),
                    propertySchema.getType());
        }

        public static DataUnitPropertySchemaWrapper of(final DataUnitPropertySchemaPersistentModel propertySchema,
                                                       final String... ignoreProperties) {
            return new DataUnitPropertySchemaWrapper(propertySchema.getId(), propertySchema.getName(),
                    propertySchema.getType());
        }
    }
}
