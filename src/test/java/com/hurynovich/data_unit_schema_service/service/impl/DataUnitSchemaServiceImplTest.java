package com.hurynovich.data_unit_schema_service.service.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.dao.DataUnitSchemaDao;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl_;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitSchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaPersistentModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaServiceModelGenerator;
import com.hurynovich.data_unit_schema_service.service.DataUnitSchemaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Optional;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;

@ExtendWith(MockitoExtension.class)
class DataUnitSchemaServiceImplTest {

    private final ModelGenerator<DataUnitSchemaServiceModel> serviceModelGenerator =
            new DataUnitSchemaServiceModelGenerator();

    private final ModelGenerator<DataUnitSchemaPersistentModel> persistentModelGenerator =
            new DataUnitSchemaPersistentModelGenerator();

    private final ModelAsserter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> schemaAsserter =
            new DataUnitSchemaAsserter();

    @Mock
    private DataUnitSchemaDao dao;

    @Mock
    private ServiceConverter<DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> converter;

    private DataUnitSchemaService service;

    @BeforeEach
    void initService() {
        service = new DataUnitSchemaServiceImpl(dao, converter);
    }

    @Test
    void saveNewSchemaTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generateWithNullId();
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generateWithNullId();
        Mockito.when(converter.convert(serviceModel)).thenReturn(persistentModel);
        Mockito.when(dao.save(persistentModel)).thenReturn(Mono.just(persistentModel));
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModelGenerator.generate());

        final DataUnitSchemaServiceModel savedSchema = service.save(serviceModel).block();
        Assertions.assertNotNull(savedSchema);

        schemaAsserter.assertEquals(serviceModel, savedSchema, DataUnitSchemaServiceModelImpl_.ID);

        Assertions.assertNotNull(savedSchema.getId());
    }

    @Test
    void updateExistingSchemaTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generate();
        Mockito.when(converter.convert(serviceModel)).thenReturn(persistentModel);
        Mockito.when(dao.save(persistentModel)).thenReturn(Mono.just(persistentModel));
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModelGenerator.generate());

        final DataUnitSchemaServiceModel savedSchema = service.save(serviceModel).block();
        Assertions.assertNotNull(savedSchema);

        schemaAsserter.assertEquals(serviceModel, savedSchema);
    }

    @Test
    void findByIdTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generate();
        final Long id = serviceModel.getId();
        Mockito.when(dao.findById(id)).thenReturn(Mono.just(persistentModel));
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModel);

        final DataUnitSchemaServiceModel schema = service.findById(id).block();
        Assertions.assertNotNull(schema);

        schemaAsserter.assertEquals(serviceModel, schema);
    }

    @Test
    void findByIdEmptyTest() {
        Mockito.when(dao.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.justOrEmpty(Optional.empty()));
        final DataUnitSchemaServiceModel schema = service.findById(DATA_UNIT_SCHEMA_ID_1).block();

        Assertions.assertNull(schema);
    }

    @Test
    void findAllTest() {
        final List<DataUnitSchemaServiceModel> existingSchemaServiceModels = serviceModelGenerator.generateList();
        final List<DataUnitSchemaPersistentModel> existingSchemaPersistentModel = persistentModelGenerator.generateList();
        final PaginationParams params1 = new PaginationParams(0, 2);
        final DataUnitSchemaPersistentModel existingSchemaPersistentModel1 = existingSchemaPersistentModel.get(0);
        final DataUnitSchemaPersistentModel existingSchemaPersistentModel2 = existingSchemaPersistentModel.get(1);
        Mockito.when(dao.findAll(params1)).thenReturn(
                Mono.just(List.of(existingSchemaPersistentModel1, existingSchemaPersistentModel2)));
        final DataUnitSchemaServiceModel existingSchemaServiceModel1 = existingSchemaServiceModels.get(0);
        Mockito.when(converter.convert(existingSchemaPersistentModel1)).thenReturn(existingSchemaServiceModel1);
        final DataUnitSchemaServiceModel existingSchemaServiceModel2 = existingSchemaServiceModels.get(1);
        Mockito.when(converter.convert(existingSchemaPersistentModel2)).thenReturn(existingSchemaServiceModel2);

        final List<DataUnitSchemaServiceModel> schemas1 = service.findAll(params1).block();
        Assertions.assertNotNull(schemas1);
        Assertions.assertEquals(2, schemas1.size());

        schemaAsserter.assertEquals(existingSchemaServiceModel1, schemas1.get(0), DataUnitSchemaEntity_.PROPERTY_SCHEMAS);
        schemaAsserter.assertEquals(existingSchemaServiceModel2, schemas1.get(1), DataUnitSchemaEntity_.PROPERTY_SCHEMAS);

        final PaginationParams params2 = new PaginationParams(2, 2);
        final DataUnitSchemaPersistentModel existingSchemaPersistentModel3 = existingSchemaPersistentModel.get(2);
        Mockito.when(dao.findAll(params2)).thenReturn(Mono.just(List.of(existingSchemaPersistentModel3)));
        final DataUnitSchemaServiceModel existingSchemaServiceModel3 = existingSchemaServiceModels.get(0);
        Mockito.when(converter.convert(existingSchemaPersistentModel3)).thenReturn(existingSchemaServiceModel3);

        final List<DataUnitSchemaServiceModel> schemas2 = service.findAll(params2).block();
        Assertions.assertNotNull(schemas2);
        Assertions.assertEquals(1, schemas2.size());

        schemaAsserter.assertEquals(existingSchemaServiceModel3, schemas2.get(0), DataUnitSchemaEntity_.PROPERTY_SCHEMAS);
    }

    @Test
    void findAllEmptyTest() {
        final PaginationParams params = new PaginationParams(0, 2);
        Mockito.when(dao.findAll(params)).thenReturn(Mono.just(List.of()));

        final List<DataUnitSchemaServiceModel> schemas = service.findAll(params).block();
        Assertions.assertNotNull(schemas);
        Assertions.assertTrue(schemas.isEmpty());
    }

    @Test
    void deleteByIdTest() {
        final Long id = serviceModelGenerator.generate().getId();
        Mockito.when(dao.deleteById(id)).thenReturn(Mono.empty());

        Assertions.assertNull(service.deleteById(id).block());
    }
}
