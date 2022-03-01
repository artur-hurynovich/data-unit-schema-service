package com.hurynovich.data_unit_schema_service.validator.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.validator.AbstractValidator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class DataUnitSchemaApiModelValidator extends AbstractValidator<DataUnitSchemaApiModel> {

    private static final int DATA_UNIT_SCHEMA_NAME_MAX_LENGTH = 20;

    @Override
    public ValidationResult validate(@NonNull final DataUnitSchemaApiModel schema) {
        final List<String> errors = new ArrayList<>();
        final String name = schema.getName();
        if (StringUtils.isBlank(name)) {
            errors.add(buildCantBeNullEmptyOrBlankError("name"));
        } else if (name.length() > DATA_UNIT_SCHEMA_NAME_MAX_LENGTH) {
            errors.add(buildCantExceedMaxLengthError("name", DATA_UNIT_SCHEMA_NAME_MAX_LENGTH));
        }

        final ValidationResult result;
        if (errors.isEmpty()) {
            result = new ValidationResult();
        } else {
            result = new ValidationResult(ValidationResultType.FAILURE, errors);
        }

        return result;
    }
}
