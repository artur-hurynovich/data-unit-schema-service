package com.hurynovich.data_unit_schema_service.dao;

import com.hurynovich.data_unit_schema_service.DataUnitSchemaServiceApplication;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitSchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaPersistentModelGenerator;
import com.hurynovich.data_unit_schema_service.test_dao.TestDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;

@SpringBootTest(classes = DataUnitSchemaServiceApplication.class)
class DataUnitSchemaMySQLDaoTest {

    private static final String UPDATED_SCHEMA_NAME = "UPD";

    private final ModelGenerator<DataUnitSchemaPersistentModel> schemaGenerator =
            new DataUnitSchemaPersistentModelGenerator();

    private final ModelAsserter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> schemaAsserter =
            new DataUnitSchemaAsserter();

    @Autowired
    private TestDao<DataUnitSchemaPersistentModel, Long> testDao;

    @Autowired
    private DataUnitSchemaDao dao;

    @Test
    void saveNewSchemaTest() {
        final DataUnitSchemaPersistentModel schema = schemaGenerator.generateWithNullId();
        final DataUnitSchemaPersistentModel savedSchema = dao.save(schema).block();
        Assertions.assertNotNull(savedSchema);

        schemaAsserter.assertEquals(schema, savedSchema, DataUnitSchemaEntity_.ID);

        Assertions.assertNotNull(savedSchema.getId());
    }

    @Test
    void updateExistingSchemaTest() {
        final DataUnitSchemaEntity existingSchema = (DataUnitSchemaEntity) testDao
                .save(schemaGenerator.generateWithNullId());
        existingSchema.setName(UPDATED_SCHEMA_NAME);
        final DataUnitSchemaPersistentModel updatedSchema = dao.save(existingSchema).block();
        Assertions.assertNotNull(updatedSchema);

        schemaAsserter.assertEquals(existingSchema, updatedSchema);
    }

    @Test
    void findByIdTest() {
        final DataUnitSchemaPersistentModel existingSchema = testDao.save(schemaGenerator.generateWithNullId());
        final DataUnitSchemaPersistentModel schema = dao.findById(existingSchema.getId()).block();
        Assertions.assertNotNull(schema);

        schemaAsserter.assertEquals(existingSchema, schema);
    }

    @Test
    void findByIdEmptyTest() {
        Assertions.assertNull(dao.findById(DATA_UNIT_SCHEMA_ID_1).block());
    }

    @Test
    void findAllTest() {
        final List<DataUnitSchemaPersistentModel> existingSchemas = schemaGenerator.generateListWithNullIds()
                .stream()
                .map(testDao::save)
                .toList();
        final List<DataUnitSchemaPersistentModel> schemas1 = dao.findAll(new PaginationParams(0, 2)).block();
        Assertions.assertNotNull(schemas1);
        Assertions.assertEquals(2, schemas1.size());

        final DataUnitSchemaPersistentModel schema1 = schemas1.get(0);
        schemaAsserter.assertEquals(existingSchemas.get(0), schema1, DataUnitSchemaEntity_.PROPERTY_SCHEMAS);
        final List<DataUnitPropertySchemaPersistentModel> propertySchemas1 =
                schema1.getPropertySchemas();
        Assertions.assertNotNull(propertySchemas1);
        Assertions.assertTrue(propertySchemas1.isEmpty());

        final DataUnitSchemaPersistentModel schema2 = schemas1.get(1);
        schemaAsserter.assertEquals(existingSchemas.get(1), schema2, DataUnitSchemaEntity_.PROPERTY_SCHEMAS);
        final List<DataUnitPropertySchemaPersistentModel> propertySchemas2 =
                schema2.getPropertySchemas();
        Assertions.assertNotNull(propertySchemas2);
        Assertions.assertTrue(propertySchemas2.isEmpty());

        final List<DataUnitSchemaPersistentModel> schemas2 = dao.findAll(new PaginationParams(2, 2)).block();
        Assertions.assertNotNull(schemas2);
        Assertions.assertEquals(1, schemas2.size());

        final DataUnitSchemaPersistentModel schema3 = schemas2.get(0);
        schemaAsserter.assertEquals(existingSchemas.get(2), schema3, DataUnitSchemaEntity_.PROPERTY_SCHEMAS);
        final List<DataUnitPropertySchemaPersistentModel> propertySchemas3 =
                schema3.getPropertySchemas();
        Assertions.assertNotNull(propertySchemas3);
        Assertions.assertTrue(propertySchemas3.isEmpty());
    }

    @Test
    void findAllEmptyTest() {
        final List<DataUnitSchemaPersistentModel> schemas = dao.findAll(new PaginationParams(0, 2)).block();
        Assertions.assertNotNull(schemas);
        Assertions.assertTrue(schemas.isEmpty());
    }

    @Test
    void deleteByIdTest() {
        final DataUnitSchemaPersistentModel existingSchema = testDao.save(schemaGenerator.generateWithNullId());
        final Long id = existingSchema.getId();
        Assertions.assertNull(dao.deleteById(id).block());

        Assertions.assertNull(testDao.findById(id));
    }

    @Test
    void countTest() {
        final List<DataUnitSchemaPersistentModel> existingSchemas = schemaGenerator.generateListWithNullIds()
                .stream()
                .map(testDao::save)
                .toList();
        Assertions.assertEquals(existingSchemas.size(), dao.count().block());
    }

    @AfterEach
    void clear() {
        testDao.deleteAll();
    }
}
