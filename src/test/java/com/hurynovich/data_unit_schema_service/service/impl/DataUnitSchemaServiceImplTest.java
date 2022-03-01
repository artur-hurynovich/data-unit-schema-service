package com.hurynovich.data_unit_schema_service.service.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.dao.DataUnitSchemaDao;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModelImpl_;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitSchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaPersistentModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaServiceModelGenerator;
import com.hurynovich.data_unit_schema_service.service.DataUnitSchemaService;
import com.hurynovich.paginator.PaginationParams;
import com.hurynovich.paginator.impl.PaginationParamsImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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
    void saveNewTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generateWithNullId();
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generateWithNullId();
        Mockito.when(converter.convert(serviceModel)).thenReturn(persistentModel);
        Mockito.when(dao.save(persistentModel)).thenReturn(Mono.just(persistentModel));
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModelGenerator.generate());

        StepVerifier
                .create(service.save(serviceModel))
                .assertNext(savedSchema -> {
                    Assertions.assertNotNull(savedSchema);

                    schemaAsserter.assertEquals(serviceModel, savedSchema, DataUnitSchemaServiceModelImpl_.ID);

                    Assertions.assertNotNull(savedSchema.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void updateExistingTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generate();
        Mockito.when(converter.convert(serviceModel)).thenReturn(persistentModel);
        Mockito.when(dao.save(persistentModel)).thenReturn(Mono.just(persistentModel));
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModelGenerator.generate());

        StepVerifier
                .create(service.save(serviceModel))
                .assertNext(savedSchema -> {
                    Assertions.assertNotNull(savedSchema);

                    schemaAsserter.assertEquals(serviceModel, savedSchema);
                })
                .expectComplete()
                .verify();
    }

    @Test
    void findByIdTest() {
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitSchemaPersistentModel persistentModel = persistentModelGenerator.generate();
        final String id = serviceModel.getId();
        Mockito.when(dao.findById(id)).thenReturn(Mono.just(persistentModel));
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModel);

        StepVerifier
                .create(service.findById(id))
                .assertNext(schema -> {
                    Assertions.assertNotNull(schema);

                    schemaAsserter.assertEquals(serviceModel, schema);
                })
                .expectComplete()
                .verify();
    }

    @Test
    void findByIdEmptyTest() {
        Mockito.when(dao.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.justOrEmpty(Optional.empty()));
        StepVerifier
                .create(service.findById(DATA_UNIT_SCHEMA_ID_1))
                .expectComplete()
                .verify();
    }

    @Test
    void findAllTest() {
        final List<DataUnitSchemaServiceModel> existingSchemaServiceModels = serviceModelGenerator.generateList();

        final List<DataUnitSchemaPersistentModel> existingSchemaPersistentModel = persistentModelGenerator.generateList();
        final PaginationParams params1 = new PaginationParamsImpl(0, 2);
        final DataUnitSchemaPersistentModel existingSchemaPersistentModel1 = existingSchemaPersistentModel.get(0);
        final DataUnitSchemaPersistentModel existingSchemaPersistentModel2 = existingSchemaPersistentModel.get(1);
        Mockito.when(dao.findAll(params1)).thenReturn(
                Mono.just(List.of(existingSchemaPersistentModel1, existingSchemaPersistentModel2)));
        final DataUnitSchemaServiceModel existingSchemaServiceModel1 = existingSchemaServiceModels.get(0);
        Mockito.when(converter.convert(existingSchemaPersistentModel1))
                .thenReturn(existingSchemaServiceModel1);
        final DataUnitSchemaServiceModel existingSchemaServiceModel2 = existingSchemaServiceModels.get(1);
        Mockito.when(converter.convert(existingSchemaPersistentModel2))
                .thenReturn(existingSchemaServiceModel2);

        StepVerifier
                .create(service.findAll(params1))
                .assertNext(schemas -> {
                    Assertions.assertNotNull(schemas);
                    Assertions.assertEquals(2, schemas.size());

                    schemaAsserter.assertEquals(existingSchemaServiceModel1, schemas.get(0));
                    schemaAsserter.assertEquals(existingSchemaServiceModel2, schemas.get(1));
                })
                .expectComplete()
                .verify();

        final PaginationParams params2 = new PaginationParamsImpl(2, 2);
        final DataUnitSchemaPersistentModel existingSchemaPersistentModel3 = existingSchemaPersistentModel.get(2);
        Mockito.when(dao.findAll(params2)).thenReturn(Mono.just(List.of(existingSchemaPersistentModel3)));
        final DataUnitSchemaServiceModel existingSchemaServiceModel3 = existingSchemaServiceModels.get(0);
        Mockito.when(converter.convert(existingSchemaPersistentModel3))
                .thenReturn(existingSchemaServiceModel3);

        StepVerifier
                .create(service.findAll(params2))
                .assertNext(schemas -> {
                    Assertions.assertNotNull(schemas);
                    Assertions.assertEquals(1, schemas.size());

                    schemaAsserter.assertEquals(existingSchemaServiceModel3, schemas.get(0));
                })
                .expectComplete()
                .verify();
    }

    @Test
    void findAllEmptyTest() {
        final PaginationParams params = new PaginationParamsImpl(0, 2);
        Mockito.when(dao.findAll(params)).thenReturn(Mono.just(List.of()));

        StepVerifier
                .create(service.findAll(params))
                .assertNext(schemas -> {
                    Assertions.assertNotNull(schemas);
                    Assertions.assertTrue(schemas.isEmpty());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void deleteByIdTest() {
        final String id = serviceModelGenerator.generate().getId();
        Mockito.when(dao.deleteById(id)).thenReturn(Mono.empty());

        StepVerifier
                .create(service.deleteById(id))
                .expectComplete()
                .verify();
    }
}
