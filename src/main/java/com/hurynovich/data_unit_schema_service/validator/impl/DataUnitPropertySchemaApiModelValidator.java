package com.hurynovich.data_unit_schema_service.validator.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaServiceModel;
import com.hurynovich.data_unit_schema_service.service.DataUnitSchemaService;
import com.hurynovich.data_unit_schema_service.validator.AbstractValidator;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import com.hurynovich.data_unit_schema_service.validator.model.ValidationResultType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataUnitPropertySchemaApiModelValidator extends AbstractValidator<DataUnitPropertySchemaApiModel> {

    private final DataUnitSchemaService schemaService;

    private static final int DATA_UNIT_PROPERTY_SCHEMA_NAME_MAX_LENGTH = 20;

    public DataUnitPropertySchemaApiModelValidator(@NonNull final DataUnitSchemaService schemaService) {
        this.schemaService = schemaService;
    }

    @Override
    public ValidationResult validate(@NonNull final DataUnitPropertySchemaApiModel propertySchema) {
        final List<String> errors = new ArrayList<>();
        final String name = propertySchema.getName();
        if (StringUtils.isBlank(name)) {
            errors.add(buildCantBeNullEmptyOrBlankError("name"));
        } else if (name.length() > DATA_UNIT_PROPERTY_SCHEMA_NAME_MAX_LENGTH) {
            errors.add(buildCantExceedMaxLengthError("name",
                    DATA_UNIT_PROPERTY_SCHEMA_NAME_MAX_LENGTH));
        }

        if (propertySchema.getType() == null) {
            errors.add("'type' can't be null");
        }

        final String schemaId = propertySchema.getSchemaId();
        if (StringUtils.isBlank(schemaId)) {
            errors.add(buildCantBeNullEmptyOrBlankError("schemaId"));
        } else {
            final DataUnitSchemaServiceModel schema = schemaService.findById(schemaId).block();
            if (schema == null) {
                errors.add("'schemaId' doesn't exist");
            }
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
