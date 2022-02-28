package com.hurynovich.data_unit_schema_service.validator.impl;

import com.hurynovich.data_unit_schema_service.model.DataUnitPropertyType;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.model_generator.ModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitPropertySchemaApiModelGenerator;
import com.hurynovich.data_unit_schema_service.model_generator.impl.DataUnitSchemaServiceModelGenerator;
import com.hurynovich.data_unit_schema_service.service.DataUnitSchemaService;
import com.hurynovich.data_unit_schema_service.validator.Validator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID;
import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME;

@ExtendWith(MockitoExtension.class)
class DataUnitPropertySchemaApiModelValidatorTest {

    private static final int DATA_UNIT_PROPERTY_SCHEMA_NAME_MAX_LENGTH = 20;

    private static final String DATA_UNIT_PROPERTY_SCHEMA_NON_VALID_NAME = "Non-valid data unit property schema name";

    private final ModelGenerator<DataUnitPropertySchemaApiModel> propertySchemaGenerator =
            new DataUnitPropertySchemaApiModelGenerator();

    private final ModelGenerator<DataUnitSchemaServiceModel> schemaGenerator =
            new DataUnitSchemaServiceModelGenerator();

    @Mock
    private DataUnitSchemaService schemaService;

    private Validator<DataUnitPropertySchemaApiModel> validator;

    @BeforeEach
    public void initValidator() {
        validator = new DataUnitPropertySchemaApiModelValidator(schemaService);
    }

    @Test
    void validateTest() {
        Mockito.when(schemaService.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(schemaGenerator.generate()));

        final ValidationResult result = validator.validate(propertySchemaGenerator.generate());

        Assertions.assertEquals(ValidationResultType.SUCCESS, result.getType());
        Assertions.assertTrue(result.getErrors().isEmpty());
    }

    @Test
    void validateNullNameTest() {
        Mockito.when(schemaService.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(schemaGenerator.generate()));

        final DataUnitPropertySchemaApiModelImpl propertySchema =
                new DataUnitPropertySchemaApiModelImpl(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, null,
                        DataUnitPropertyType.TEXT, DATA_UNIT_SCHEMA_ID_1);
        final ValidationResult result = validator.validate(propertySchema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'name' can't be null, empty or blank", errors.get(0));
    }

    @Test
    void validateEmptyNameTest() {
        Mockito.when(schemaService.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(schemaGenerator.generate()));

        final DataUnitPropertySchemaApiModelImpl propertySchema =
                new DataUnitPropertySchemaApiModelImpl(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, StringUtils.EMPTY,
                        DataUnitPropertyType.TEXT, DATA_UNIT_SCHEMA_ID_1);
        final ValidationResult result = validator.validate(propertySchema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'name' can't be null, empty or blank", errors.get(0));
    }

    @Test
    void validateBlankNameTest() {
        Mockito.when(schemaService.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(schemaGenerator.generate()));

        final DataUnitPropertySchemaApiModelImpl propertySchema =
                new DataUnitPropertySchemaApiModelImpl(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID, StringUtils.SPACE,
                        DataUnitPropertyType.TEXT, DATA_UNIT_SCHEMA_ID_1);
        final ValidationResult result = validator.validate(propertySchema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'name' can't be null, empty or blank", errors.get(0));
    }

    @Test
    void validateNameExceedsMaxLengthTest() {
        Mockito.when(schemaService.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(schemaGenerator.generate()));

        final DataUnitPropertySchemaApiModelImpl propertySchema =
                new DataUnitPropertySchemaApiModelImpl(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID,
                        DATA_UNIT_PROPERTY_SCHEMA_NON_VALID_NAME, DataUnitPropertyType.TEXT, DATA_UNIT_SCHEMA_ID_1);
        final ValidationResult result = validator.validate(propertySchema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'name' length can't exceed " + DATA_UNIT_PROPERTY_SCHEMA_NAME_MAX_LENGTH,
                errors.get(0));
    }

    @Test
    void validateNullTypeTest() {
        Mockito.when(schemaService.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.just(schemaGenerator.generate()));

        final DataUnitPropertySchemaApiModelImpl propertySchema =
                new DataUnitPropertySchemaApiModelImpl(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID,
                        DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, null, DATA_UNIT_SCHEMA_ID_1);
        final ValidationResult result = validator.validate(propertySchema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'type' can't be null", errors.get(0));
    }

    @Test
    void validateNullSchemaIdTest() {
        final DataUnitPropertySchemaApiModelImpl propertySchema =
                new DataUnitPropertySchemaApiModelImpl(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID,
                        DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DataUnitPropertyType.TEXT, null);
        final ValidationResult result = validator.validate(propertySchema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'schemaId' can't be null, empty or blank", errors.get(0));
    }

    @Test
    void validateSchemaIdDoesNotExistTest() {
        Mockito.when(schemaService.findById(DATA_UNIT_SCHEMA_ID_1)).thenReturn(Mono.empty());

        final DataUnitPropertySchemaApiModelImpl propertySchema =
                new DataUnitPropertySchemaApiModelImpl(DATA_UNIT_TEXT_PROPERTY_SCHEMA_ID,
                        DATA_UNIT_TEXT_PROPERTY_SCHEMA_NAME, DataUnitPropertyType.TEXT, DATA_UNIT_SCHEMA_ID_1);
        final ValidationResult result = validator.validate(propertySchema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'schemaId' doesn't exist", errors.get(0));
    }
}
