package com.hurynovich.data_unit_schema_service.request_handler.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hurynovich.data_unit_schema_service.converter.ApiConverter;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_asserter.ModelAsserter;
import com.hurynovich.data_unit_schema_service.model_asserter.impl.DataUnitSchemaAsserter;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaApiModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaServiceModelGenerator;
import com.hurynovich.data_unit_schema_service.request_handler.DataUnitSchemaRequestHandler;
import com.hurynovich.data_unit_schema_service.service.DataUnitSchemaService;
import com.hurynovich.data_unit_schema_service.utils.ServerResponseUtils;
import com.hurynovich.data_unit_schema_service.validator.Validator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import com.hurynovich.paginator.GenericPage;
import com.hurynovich.paginator.PaginationParams;
import com.hurynovich.paginator.Paginator;
import com.hurynovich.paginator.impl.GenericPageBuilderImpl;
import com.hurynovich.paginator.impl.PaginationParamsImpl;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_2;

@ExtendWith(MockitoExtension.class)
class DataUnitSchemaRequestHandlerImplTest {

    private static final String URI_PREFIX = "http://localhost:8080/api/v1/data_unit_schema";

    private static final String PAGE_NUMBER_REQUEST_PARAM = "pageNumber";

    private static final int DATA_UNIT_SCHEMAS_PER_PAGE = 20;

    private static final String TEST_ERROR = "error";

    private final ModelGenerator<DataUnitSchemaApiModel> apiModelGenerator =
            new DataUnitSchemaApiModelGenerator();

    private final ModelGenerator<DataUnitSchemaServiceModel> serviceModelGenerator =
            new DataUnitSchemaServiceModelGenerator();

    private final ModelAsserter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel, DataUnitSchemaPersistentModel> asserter =
            new DataUnitSchemaAsserter();

    private final ObjectMapper mapper = new ObjectMapper();

    @Mock
    private Validator<DataUnitSchemaApiModel> validator;

    @Mock
    private DataUnitSchemaService service;

    @Mock
    private ApiConverter<DataUnitSchemaApiModel, DataUnitSchemaServiceModel> converter;

    @Mock
    private Paginator paginator;

    @Mock
    private ServerRequest request;

    private DataUnitSchemaRequestHandler handler;

    @BeforeEach
    public void initRequestHandler() {
        handler = new DataUnitSchemaRequestHandlerImpl(validator, service, converter, paginator);
    }

    @Test
    void postTest() {
        final DataUnitSchemaApiModel apiModel = apiModelGenerator.generateWithNullId();
        Mockito.when(request.bodyToMono(DataUnitSchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        Mockito.when(validator.validate(apiModel)).thenReturn(new ValidationResult());
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generateWithNullId();
        Mockito.when(converter.convert(apiModel)).thenReturn(serviceModel);
        final DataUnitSchemaServiceModel savedServiceModel = serviceModelGenerator.generate();
        Mockito.when(service.save(serviceModel)).thenReturn(Mono.just(savedServiceModel));
        Mockito.when(request.uri()).thenReturn(URI.create(URI_PREFIX));

        StepVerifier
                .create(handler.post(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    Assertions.assertEquals(HttpStatus.CREATED, response.statusCode());
                    Assertions.assertEquals(URI.create(URI_PREFIX + "/" + DATA_UNIT_SCHEMA_ID_1),
                            response.headers().getLocation());

                    final DataUnitSchemaApiModel savedApiModel = extractSchemaResponseBody(response);
                    asserter.assertEquals(apiModel, savedApiModel, DataUnitSchemaApiModelImpl_.ID);

                    Assertions.assertNotNull(savedApiModel.getId());
                })
                .expectComplete()
                .verify();
    }

    @Test
    void postNotNullIdTest() {
        Mockito.when(request.bodyToMono(DataUnitSchemaApiModel.class))
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
        final DataUnitSchemaApiModel apiModel = apiModelGenerator.generateWithNullId();
        Mockito.when(request.bodyToMono(DataUnitSchemaApiModel.class)).thenReturn(Mono.just(apiModel));
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
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
        Mockito.when(service.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(serviceModel));
        final DataUnitSchemaApiModel apiModel = apiModelGenerator.generate();
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
    public void getAllTest() {
        Mockito.when(request.queryParam(PAGE_NUMBER_REQUEST_PARAM)).thenReturn(Optional.of("1"));
        final PaginationParams params = new PaginationParamsImpl(0, DATA_UNIT_SCHEMAS_PER_PAGE);
        Mockito.when(paginator.buildParams(1, DATA_UNIT_SCHEMAS_PER_PAGE)).thenReturn(params);
        final List<DataUnitSchemaServiceModel> serviceModels = serviceModelGenerator.generateList();
        Mockito.when(service.findAll(params)).thenReturn(Mono.just(serviceModels));
        Mockito.when(service.count()).thenReturn(Mono.just(10L));

        final List<DataUnitSchemaApiModel> apiModels = apiModelGenerator.generateList();
        for (int i = 0; i < serviceModels.size(); i++) {
            Mockito.when(converter.convert(serviceModels.get(i))).thenReturn(apiModels.get(i));
        }

        final GenericPage<DataUnitSchemaApiModel> page = new GenericPageBuilderImpl<DataUnitSchemaApiModel>()
                .withElements(apiModels)
                .withTotalElementsCount(10L)
                .withCurrentPageNumber(1L)
                .withTotalPagesCount(4L)
                .build();
        Mockito.when(paginator.buildPage(apiModels, 10L, params)).thenReturn(page);

        StepVerifier
                .create(handler.getAll(request))
                .assertNext(response -> {
                    Assertions.assertNotNull(response);
                    final GenericPage<DataUnitSchemaApiModel> responsePage = extractGenericPageResponseBody(response);
                    Assertions.assertNotNull(responsePage);

                    final List<DataUnitSchemaApiModel> expectedSchemas = page.getElements();
                    final List<DataUnitSchemaApiModel> actualSchemas = responsePage.getElements();
                    Assertions.assertEquals(expectedSchemas.size(), actualSchemas.size());
                    for (int i = 0; i < expectedSchemas.size(); i++) {
                        asserter.assertEquals(expectedSchemas.get(i), actualSchemas.get(i));
                    }

                    Assertions.assertEquals(page.getTotalElementsCount(), responsePage.getTotalElementsCount());
                    Assertions.assertEquals(page.getCurrentPageNumber(), responsePage.getCurrentPageNumber());
                    Assertions.assertEquals(page.getTotalPagesCount(), responsePage.getTotalPagesCount());
                })
                .expectComplete()
                .verify();
    }

    @Test
    public void putTest() {
        final DataUnitSchemaApiModel apiModel = apiModelGenerator.generate();
        Mockito.when(request.bodyToMono(DataUnitSchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        Mockito.when(request.pathVariable(DataUnitSchemaApiModelImpl_.ID)).thenReturn(DATA_UNIT_SCHEMA_ID_1);
        Mockito.when(validator.validate(apiModel)).thenReturn(new ValidationResult());
        final DataUnitSchemaServiceModel serviceModel = serviceModelGenerator.generate();
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
        final DataUnitSchemaApiModel apiModel = apiModelGenerator.generateWithNullId();
        Mockito.when(request.bodyToMono(DataUnitSchemaApiModel.class)).thenReturn(Mono.just(apiModel));

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
        final DataUnitSchemaApiModel apiModel = apiModelGenerator.generate();
        Mockito.when(request.bodyToMono(DataUnitSchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        Mockito.when(request.pathVariable(DataUnitSchemaApiModelImpl_.ID)).thenReturn(DATA_UNIT_SCHEMA_ID_2);

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
        final DataUnitSchemaApiModel apiModel = apiModelGenerator.generate();
        Mockito.when(request.bodyToMono(DataUnitSchemaApiModel.class)).thenReturn(Mono.just(apiModel));
        Mockito.when(request.pathVariable(DataUnitSchemaApiModelImpl_.ID)).thenReturn(DATA_UNIT_SCHEMA_ID_1);
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

    private DataUnitSchemaApiModel extractSchemaResponseBody(final ServerResponse response) {
        try {
            final String responseBody = ServerResponseUtils.extractResponseBody(response);
            Assertions.assertNotNull(responseBody);

            return mapper.readValue(responseBody, DataUnitSchemaApiModelImpl.class);
        } catch (final JsonProcessingException e) {
            throw new RuntimeException("Failed to parse response body", e);
        }
    }

    /*
     * ObjectMapper can't build GenericPage from JSON because GenericPage can only
     * be built with GenericPageBuilder. So we have to parse JSON ourselves.
     */
    private GenericPage<DataUnitSchemaApiModel> extractGenericPageResponseBody(final ServerResponse response) {
        try {
            final JsonNode rootNode = mapper.readTree(ServerResponseUtils.extractResponseBody(response));
            final JsonNode elementsNode = rootNode.get("elements");
            final List<DataUnitSchemaApiModel> schemas = StreamSupport.stream(elementsNode.spliterator(), false).
                    map(this::buildSchema).
                    collect(Collectors.toList());

            final Long totalElementsCount = buildNullableLong(rootNode.get("totalElementsCount"));
            final Long currentPageNumber = buildNullableLong(rootNode.get("currentPageNumber"));
            final Long totalPagesCount = buildNullableLong(rootNode.get("totalPagesCount"));

            return new GenericPageBuilderImpl<DataUnitSchemaApiModel>()
                    .withElements(schemas)
                    .withTotalElementsCount(totalElementsCount)
                    .withCurrentPageNumber(currentPageNumber)
                    .withTotalPagesCount(totalPagesCount)
                    .build();
        } catch (final JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private DataUnitSchemaApiModel buildSchema(final JsonNode schemaNode) {
        return new DataUnitSchemaApiModelImpl(schemaNode.get(DataUnitSchemaApiModelImpl_.ID).asText(),
                schemaNode.get(DataUnitSchemaApiModelImpl_.NAME).asText());
    }

    private Long buildNullableLong(final JsonNode node) {
        final String asText = node.asText();

        return asText.equals("null") ? null : Long.valueOf(asText);
    }
}
