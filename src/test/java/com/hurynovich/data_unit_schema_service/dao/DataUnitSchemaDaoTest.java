package com.hurynovich.data_unit_schema_service.dao;

import com.hurynovich.data_unit_schema_service.DataUnitSchemaServiceApplication;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitSchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaGenerator;
import com.hurynovich.data_unit_schema_service.test_dao.TestDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;

@SpringBootTest(classes = DataUnitSchemaServiceApplication.class)
public class DataUnitSchemaDaoTest {

    private final ModelGenerator<DataUnitSchemaPersistentModel> schemaGenerator =
            new DataUnitSchemaGenerator();

    private final ModelAsserter<DataUnitSchemaPersistentModel> schemaAsserter =
            new DataUnitSchemaAsserter();

    @Autowired
    private TestDao<DataUnitSchemaPersistentModel, Long> testDao;

    @Autowired
    private DataUnitSchemaDao dao;

    @Test
    public void saveTest() {
        final DataUnitSchemaPersistentModel schema = schemaGenerator.generateWithNullId();
        final DataUnitSchemaPersistentModel savedSchema = dao.save(schema).block();
        Assertions.assertNotNull(savedSchema);

        schemaAsserter.assertEquals(schema, savedSchema, DataUnitSchemaEntity_.ID);

        Assertions.assertNotNull(savedSchema.getId());
    }

    @Test
    public void findByIdTest() {
        final DataUnitSchemaPersistentModel existingSchema = testDao.save(schemaGenerator.generateWithNullId());
        final DataUnitSchemaPersistentModel schema = dao.findById(existingSchema.getId()).block();
        Assertions.assertNotNull(schema);

        schemaAsserter.assertEquals(existingSchema, schema);
    }

    @Test
    public void findByIdEmptyTest() {
        final DataUnitSchemaPersistentModel schema = dao.findById(DATA_UNIT_SCHEMA_ID_1).block();
        Assertions.assertNull(schema);
    }

    @Test
    public void findAllTest() {
        final List<DataUnitSchemaPersistentModel> existingSchemas = schemaGenerator.generateListWithNullIds()
                .stream()
                .map(testDao::save)
                .toList();
        final List<DataUnitSchemaPersistentModel> schemas1 = dao.findAll(new PaginationParams(0, 2)).block();
        Assertions.assertNotNull(schemas1);
        Assertions.assertEquals(2, schemas1.size());

        schemaAsserter.assertEquals(existingSchemas.get(0), schemas1.get(0), DataUnitSchemaEntity_.PROPERTY_SCHEMAS);
        schemaAsserter.assertEquals(existingSchemas.get(1), schemas1.get(1), DataUnitSchemaEntity_.PROPERTY_SCHEMAS);

        final List<DataUnitSchemaPersistentModel> schemas2 = dao.findAll(new PaginationParams(2, 2)).block();
        Assertions.assertNotNull(schemas2);
        Assertions.assertEquals(1, schemas2.size());

        schemaAsserter.assertEquals(existingSchemas.get(2), schemas2.get(0), DataUnitSchemaEntity_.PROPERTY_SCHEMAS);
    }

    @Test
    public void deleteByIdTest() {
        final DataUnitSchemaPersistentModel existingSchema = testDao.save(schemaGenerator.generateWithNullId());
        dao.delete(existingSchema).block();

        Assertions.assertNull(testDao.findById(existingSchema.getId()));
    }

    @Test
    public void countTest() {
        final List<DataUnitSchemaPersistentModel> existingSchemas = schemaGenerator.generateListWithNullIds()
                .stream()
                .map(testDao::save)
                .toList();
        Assertions.assertEquals(existingSchemas.size(), dao.count().block());
    }

    @AfterEach
    public void clear() {
        testDao.deleteAll();
    }
}
