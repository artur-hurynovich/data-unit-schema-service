package com.hurynovich.data_unit_schema_service.validator.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.validator.Validator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
class DataUnitSchemaApiModelValidator implements Validator<DataUnitSchemaApiModel> {

    private static final int DATA_UNIT_SCHEMA_NAME_MAX_LENGTH = 20;

    @Override
    public ValidationResult validate(@NonNull final DataUnitSchemaApiModel schema) {
        final ValidationResult result = new ValidationResult();
        final String name = schema.getName();
        if (StringUtils.isBlank(name)) {
            result.setType(ValidationResultType.FAILURE);
            result.addError("'name' can't be null, empty or blank");
        } else if (name.length() > DATA_UNIT_SCHEMA_NAME_MAX_LENGTH) {
            result.setType(ValidationResultType.FAILURE);
            result.addError("'name' length can't exceed " + DATA_UNIT_SCHEMA_NAME_MAX_LENGTH + " characters");
        }

        return result;
    }
}
