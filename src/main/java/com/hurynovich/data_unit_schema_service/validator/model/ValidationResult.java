package com.hurynovich.data_unit_schema_service.validator.model;

import org.springframework.lang.NonNull;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {

    private ValidationResultType type = ValidationResultType.SUCCESS;

    private final List<String> errors = new ArrayList<>();

    public ValidationResultType getType() {
        return type;
    }

    public void setType(@NonNull final ValidationResultType type) {
        this.type = type;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(@NonNull final String error) {
        errors.add(error);
    }
}
