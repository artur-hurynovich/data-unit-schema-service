package com.hurynovich.data_unit_schema_service.validator.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.validator.Validator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class DataUnitSchemaApiModelValidator implements Validator<DataUnitSchemaApiModel> {

    private static final int DATA_UNIT_SCHEMA_NAME_MAX_LENGTH = 20;

    @Override
    public ValidationResult validate(@NonNull final DataUnitSchemaApiModel schema) {
        final ValidationResult result;
        final String name = schema.getName();
        if (StringUtils.isBlank(name)) {
            result = new ValidationResult(ValidationResultType.FAILURE, List.of("'name' can't be null, empty or blank"));
        } else if (name.length() > DATA_UNIT_SCHEMA_NAME_MAX_LENGTH) {
            result = new ValidationResult(ValidationResultType.FAILURE, List.of("'name' length can't exceed " +
                    DATA_UNIT_SCHEMA_NAME_MAX_LENGTH + " characters"));
        } else {
            result = new ValidationResult(ValidationResultType.SUCCESS, List.of());
        }

        return result;
    }
}
