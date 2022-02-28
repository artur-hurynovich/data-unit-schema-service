package com.hurynovich.data_unit_schema_service.service.impl;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.dao.DataUnitPropertySchemaDao;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModelImpl_;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitPropertySchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaPersistentModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaServiceModelGenerator;
import com.hurynovich.data_unit_schema_service.service.DataUnitPropertySchemaService;
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
class DataUnitPropertySchemaServiceImplTest {

    private final ModelGenerator<DataUnitPropertySchemaServiceModel> serviceModelGenerator =
            new DataUnitPropertySchemaServiceModelGenerator();

    private final ModelGenerator<DataUnitPropertySchemaPersistentModel> persistentModelGenerator =
            new DataUnitPropertySchemaPersistentModelGenerator();

    private final ModelAsserter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> schemaAsserter =
            new DataUnitPropertySchemaAsserter();

    @Mock
    private DataUnitPropertySchemaDao dao;

    @Mock
    private ServiceConverter<DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> converter;

    private DataUnitPropertySchemaService service;

    @BeforeEach
    void initService() {
        service = new DataUnitPropertySchemaServiceImpl(dao, converter);
    }

    @Test
    void saveNewTest() {
        final DataUnitPropertySchemaServiceModel serviceModel = serviceModelGenerator.generateWithNullId();
        final DataUnitPropertySchemaPersistentModel persistentModel = persistentModelGenerator.generateWithNullId();
        Mockito.when(converter.convert(serviceModel)).thenReturn(persistentModel);
        Mockito.when(dao.save(persistentModel)).thenReturn(Mono.just(persistentModel));
        Mockito.when(converter.convert(persistentModel)).thenReturn(serviceModelGenerator.generate());

        StepVerifier
                .create(service.save(serviceModel))
                .assertNext(savedSchema -> {
                    Assertions.assertNotNull(savedSchema);

                    schemaAsserter.assertEquals(serviceModel, savedSchema, DataUnitPropertySchemaServiceModelImpl_.ID);

                    Assertions.assertNotNull(savedSchema.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void updateExistingTest() {
        final DataUnitPropertySchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitPropertySchemaPersistentModel persistentModel = persistentModelGenerator.generate();
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
        final DataUnitPropertySchemaServiceModel serviceModel = serviceModelGenerator.generate();
        final DataUnitPropertySchemaPersistentModel persistentModel = persistentModelGenerator.generate();
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
    void findAllBySchemaIdTest() {
        final List<DataUnitPropertySchemaServiceModel> existingSchemaServiceModels = serviceModelGenerator.generateList();

        final List<DataUnitPropertySchemaPersistentModel> existingSchemaPersistentModel = persistentModelGenerator.generateList();
        final DataUnitPropertySchemaPersistentModel existingSchemaPersistentModel1 = existingSchemaPersistentModel.get(0);
        final DataUnitPropertySchemaPersistentModel existingSchemaPersistentModel2 = existingSchemaPersistentModel.get(1);
        final DataUnitPropertySchemaPersistentModel existingSchemaPersistentModel3 = existingSchemaPersistentModel.get(2);
        Mockito.when(dao.findAllBySchemaId(DATA_UNIT_SCHEMA_ID_1)).thenReturn(
                Mono.just(List.of(existingSchemaPersistentModel1, existingSchemaPersistentModel2,
                        existingSchemaPersistentModel3)));
        final DataUnitPropertySchemaServiceModel existingSchemaServiceModel1 = existingSchemaServiceModels.get(0);
        Mockito.when(converter.convert(existingSchemaPersistentModel1))
                .thenReturn(existingSchemaServiceModel1);
        final DataUnitPropertySchemaServiceModel existingSchemaServiceModel2 = existingSchemaServiceModels.get(1);
        Mockito.when(converter.convert(existingSchemaPersistentModel2))
                .thenReturn(existingSchemaServiceModel2);
        final DataUnitPropertySchemaServiceModel existingSchemaServiceModel3 = existingSchemaServiceModels.get(0);
        Mockito.when(converter.convert(existingSchemaPersistentModel3))
                .thenReturn(existingSchemaServiceModel3);

        StepVerifier
                .create(service.findAllBySchemaId(DATA_UNIT_SCHEMA_ID_1))
                .assertNext(schemas -> {
                    Assertions.assertNotNull(schemas);
                    Assertions.assertEquals(3, schemas.size());

                    schemaAsserter.assertEquals(existingSchemaServiceModel1, schemas.get(0));
                    schemaAsserter.assertEquals(existingSchemaServiceModel2, schemas.get(1));
                    schemaAsserter.assertEquals(existingSchemaServiceModel3, schemas.get(2));
                })
                .expectComplete()
                .verify();
    }

    @Test
    void findAllBySchemaIdEmptyTest() {
        Mockito.when(dao.findAllBySchemaId(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(List.of()));

        StepVerifier
                .create(service.findAllBySchemaId(DATA_UNIT_SCHEMA_ID_1))
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
