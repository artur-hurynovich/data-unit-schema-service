package com.hurynovich.data_unit_schema_service.validator.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.validator.Validator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.hurynovich.data_unit_schema_service.model_generator.ModelConstants.DATA_UNIT_SCHEMA_ID_1;

class DataUnitSchemaApiModelValidatorTest {

    private static final int DATA_UNIT_SCHEMA_NAME_MAX_LENGTH = 20;

    private static final String DATA_UNIT_SCHEMA_VALID_NAME = "Valid name";

    private static final String DATA_UNIT_SCHEMA_NON_VALID_NAME = "Non-valid data unit schema name";

    private final Validator<DataUnitSchemaApiModel> validator = new DataUnitSchemaApiModelValidator();

    @Test
    void validateTest() {
        final DataUnitSchemaApiModelImpl schema = new DataUnitSchemaApiModelImpl(DATA_UNIT_SCHEMA_ID_1,
                DATA_UNIT_SCHEMA_VALID_NAME);
        final ValidationResult result = validator.validate(schema);

        Assertions.assertEquals(ValidationResultType.SUCCESS, result.getType());
        Assertions.assertTrue(result.getErrors().isEmpty());
    }

    @Test
    void validateNullNameTest() {
        final DataUnitSchemaApiModelImpl schema = new DataUnitSchemaApiModelImpl(DATA_UNIT_SCHEMA_ID_1, null);
        final ValidationResult result = validator.validate(schema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'name' can't be null, empty or blank", errors.get(0));
    }

    @Test
    void validateEmptyNameTest() {
        final DataUnitSchemaApiModelImpl schema = new DataUnitSchemaApiModelImpl(DATA_UNIT_SCHEMA_ID_1,
                StringUtils.EMPTY);
        final ValidationResult result = validator.validate(schema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'name' can't be null, empty or blank", errors.get(0));
    }

    @Test
    void validateBlankNameTest() {
        final DataUnitSchemaApiModelImpl schema = new DataUnitSchemaApiModelImpl(DATA_UNIT_SCHEMA_ID_1,
                StringUtils.SPACE);
        final ValidationResult result = validator.validate(schema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'name' can't be null, empty or blank", errors.get(0));
    }

    @Test
    void validateNameExceedsMaxLengthTest() {
        final DataUnitSchemaApiModelImpl schema = new DataUnitSchemaApiModelImpl(DATA_UNIT_SCHEMA_ID_1,
                DATA_UNIT_SCHEMA_NON_VALID_NAME);
        final ValidationResult result = validator.validate(schema);

        Assertions.assertEquals(ValidationResultType.FAILURE, result.getType());

        final List<String> errors = result.getErrors();
        Assertions.assertEquals(1, errors.size());
        Assertions.assertEquals("'name' length can't exceed " +
                DATA_UNIT_SCHEMA_NAME_MAX_LENGTH + " characters", errors.get(0));
    }
}
