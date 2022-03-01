package com.hurynovich.data_unit_schema_service.request_handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl_;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl_;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitPropertySchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaApiModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaServiceModelGenerator;
import com.hurynovich.data_unit_schema_service.request_handler.DataUnitPropertySchemaRequestHandler;
import com.hurynovich.data_unit_schema_service.service.DataUnitPropertySchemaService;
import com.hurynovich.data_unit_schema_service.utils.ServerResponseUtils;
import com.hurynovich.data_unit_schema_service.validator.Validator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID;

@ExtendWith(MockitoExtension.class)
public class DataUnitPropertySchemaRequestHandlerImplTest {

    private static final String URI_PREFIX = "http://localhost:8080/api/v1/data_unit_schema";

    private static final String SCHEMA_ID_REQUEST_PARAM = DataUnitPropertySchemaApiModelImpl_.SCHEMA_ID;

    private static final String TEST_ERROR = "error";

    private final ModelGenerator<DataUnitPropertySchemaApiModel> apiModelGenerator =
            new DataUnitPropertySchemaApiModelGenerator();

    private final ModelGenerator<DataUnitPropertySchemaServiceModel> serviceModelGenerator =
            new DataUnitPropertySchemaServiceModelGenerator();

    private final ModelAsserter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel, DataUnitPropertySchemaPersistentModel> asserter =
            new DataUnitPropertySchemaAsserter();

    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private Validator<DataUnitPropertySchemaApiModel> validator;

    @Mock
    private DataUnitPropertySchemaService service;

    @Mock
    private ApiConverter<DataUnitPropertySchemaApiModel, DataUnitPropertySchemaServiceModel> converter;

    @Mock
    private ServerRequest request;

    private DataUnitPropertySchemaRequestHandler handler;

    @BeforeEach
    public void initRequestHandler() {
        handler = new DataUnitPropertySchemaRequestHandlerImpl(validator, service, converter);
    }

    @Test
    void postTest() {
        final DataUnitPropertySchemaApiModel apiModel = apiModelGenerator.generateWithNullId();
        Mockito.when(request.bodyToMono(DataUnitPropertySchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        Mockito.when(validator.validate(apiModel)).thenReturn(new ValidationResult());
        final DataUnitPropertySchemaServiceModel serviceModel = serviceModelGenerator.generateWithNullId();
        Mockito.when(converter.convert(apiModel)).thenReturn(serviceModel);
        final DataUnitPropertySchemaServiceModel savedServiceModel = serviceModelGenerator.generate();
        Mockito.when(service.save(serviceModel)).thenReturn(Mono.just(savedServiceModel));
        Mockito.when(request.uri()).thenReturn(URI.create(URI_PREFIX));

        StepVerifier
                .create(handler.post(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.CREATED, response.statusCode());
                    Assertions.assertEquals(URI.create(URI_PREFIX + "/" + DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID),
                            response.headers().getLocation());

                    final DataUnitPropertySchemaApiModel savedApiModel = extractSchemaResponseBody(response);
                    asserter.assertEquals(apiModel, savedApiModel, DataUnitSchemaApiModelImpl_.ID);

                    Assertions.assertNotNull(savedApiModel.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void postNotNullIdTest() {
        Mockito.when(request.bodyToMono(DataUnitPropertySchemaApiModel.class))
                .thenReturn(Mono.just(apiModelGenerator.generate()));

        StepVerifier
                .create(handler.post(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.statusCode());
                    Assertions.assertEquals(List.of("\"'id' should be null\"").toString(),
                            ServerResponseUtils.extractResponseBody(response));
                })
                .expectComplete()
                .verify();
    }

    @Test
    void postNotValidTest() {
        final DataUnitPropertySchemaApiModel apiModel = apiModelGenerator.generateWithNullId();
        Mockito.when(request.bodyToMono(DataUnitPropertySchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        final ValidationResult result = new ValidationResult(ValidationResultType.FAILURE, List.of(TEST_ERROR));
        Mockito.when(validator.validate(apiModel)).thenReturn(result);

        StepVerifier
                .create(handler.post(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.statusCode());
                    Assertions.assertEquals(List.of("\"" + TEST_ERROR + "\"").toString(),
                            ServerResponseUtils.extractResponseBody(response));
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void getByIdTest() {
        Mockito.when(request.pathVariable(DataUnitSchemaApiModelImpl_.ID)).thenReturn(DATA_UNIT_SCHEMA_ID_1);
        final DataUnitPropertySchemaServiceModel serviceModel = serviceModelGenerator.generate();
        Mockito.when(service.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(serviceModel));
        final DataUnitPropertySchemaApiModel apiModel = apiModelGenerator.generate();
        Mockito.when(converter.convert(serviceModel)).thenReturn(apiModel);

        StepVerifier
                .create(handler.getById(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.OK, response.statusCode());

                    asserter.assertEquals(apiModel, extractSchemaResponseBody(response));
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void getAllBySchemaIdTest() {
        Mockito.when(request.queryParam(SCHEMA_ID_REQUEST_PARAM)).thenReturn(Optional.of(DATA_UNIT_SCHEMA_ID_1));
        final List<DataUnitPropertySchemaServiceModel> serviceModels = serviceModelGenerator.generateList();
        Mockito.when(service.findAllBySchemaId(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(serviceModels));

        final List<DataUnitPropertySchemaApiModel> apiModels = apiModelGenerator.generateList();
        for (int i = 0; i < serviceModels.size(); i++) {
            Mockito.when(converter.convert(serviceModels.get(i))).thenReturn(apiModels.get(i));
        }

        StepVerifier
                .create(handler.getAllBySchemaId(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);

                    final List<DataUnitPropertySchemaApiModel> actualSchemas = extractSchemasResponseBody(response);
                    Assertions.assertEquals(apiModels.size(), actualSchemas.size());
                    for (int i = 0; i < apiModels.size(); i++) {
                        asserter.assertEquals(apiModels.get(i), actualSchemas.get(i));
                    }
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void putTest() {
        final DataUnitPropertySchemaApiModel apiModel = apiModelGenerator.generate();
        Mockito.when(request.bodyToMono(DataUnitPropertySchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        Mockito.when(request.pathVariable(DataUnitPropertySchemaApiModelImpl_.ID))
                .thenReturn(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID);
        Mockito.when(validator.validate(apiModel)).thenReturn(new ValidationResult());
        final DataUnitPropertySchemaServiceModel serviceModel = serviceModelGenerator.generate();
        Mockito.when(converter.convert(apiModel)).thenReturn(serviceModel);
        Mockito.when(service.save(serviceModel)).thenReturn(Mono.just(serviceModel));

        StepVerifier
                .create(handler.put(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.OK, response.statusCode());

                    asserter.assertEquals(apiModel, extractSchemaResponseBody(response));
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void putNullIdTest() {
        final DataUnitPropertySchemaApiModel apiModel = apiModelGenerator.generateWithNullId();
        Mockito.when(request.bodyToMono(DataUnitPropertySchemaApiModel.class)).thenReturn(Mono.just(apiModel));

        StepVerifier
                .create(handler.put(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.statusCode());
                    Assertions.assertEquals(List.of("\"'id' can't be null\"").toString(),
                            ServerResponseUtils.extractResponseBody(response));
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void putIdNotEqualToPathVariableTest() {
        final DataUnitPropertySchemaApiModel apiModel = apiModelGenerator.generate();
        Mockito.when(request.bodyToMono(DataUnitPropertySchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        Mockito.when(request.pathVariable(DataUnitSchemaApiModelImpl_.ID))
                .thenReturn(DATA_UNIT_INTEGER_PROPERTY_SCHEMA_ID);

        StepVerifier
                .create(handler.put(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.statusCode());
                    Assertions.assertEquals(List.of("\"'id' should be equal to path variable\"").toString(),
                            ServerResponseUtils.extractResponseBody(response));
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void putNotValidTest() {
        final DataUnitPropertySchemaApiModel apiModel = apiModelGenerator.generate();
        Mockito.when(request.bodyToMono(DataUnitPropertySchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        Mockito.when(request.pathVariable(DataUnitSchemaApiModelImpl_.ID))
                .thenReturn(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID);
        final ValidationResult result = new ValidationResult(ValidationResultType.FAILURE, List.of(TEST_ERROR));
        Mockito.when(validator.validate(apiModel)).thenReturn(result);

        StepVerifier
                .create(handler.put(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.statusCode());
                    Assertions.assertEquals(List.of("\"" + TEST_ERROR + "\"").toString(),
                            ServerResponseUtils.extractResponseBody(response));
                })
                .expectComplete()
                .verify();
    }

    private DataUnitPropertySchemaApiModel extractSchemaResponseBody(final ServerResponse response) {
        try {
            final String responseBody = ServerResponseUtils.extractResponseBody(response);
            Assertions.assertNotNull(responseBody);

            return mapper.readValue(responseBody, DataUnitPropertySchemaApiModelImpl.class);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Failed to parse response body", e);
        }
    }

    private List<DataUnitPropertySchemaApiModel> extractSchemasResponseBody(final ServerResponse response) {
        try {
            final String responseBody = ServerResponseUtils.extractResponseBody(response);
            Assertions.assertNotNull(responseBody);

            return mapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Failed to parse response body", e);
        }
    }
}
