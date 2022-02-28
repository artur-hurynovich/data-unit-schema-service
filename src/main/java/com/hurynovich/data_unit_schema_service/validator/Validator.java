package com.hurynovich.data_unit_schema_service.validator;

import com.hurynovich.data_unit_schema_service.validator.model.ValidationResult;
import org.springframework.lang.NonNull;

public interface Validator<T> {

    ValidationResult validate(@NonNull T t);
}
