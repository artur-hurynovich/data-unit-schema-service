package com.hurynovich.data_unit_schema_service.validator.model;

import org.springframework.lang.NonNull;

import java.util.List;

public record ValidationResult(@NonNull ValidationResultType type, @NonNull List<String> errors) {

    public ValidationResult() {
        this(ValidationResultType.SUCCESS, List.of());
    }
}
