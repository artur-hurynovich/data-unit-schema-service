package com.hurynovich.data_unit_schema_service.validator;

import org.springframework.lang.NonNull;

public abstract class AbstractValidator<T> implements Validator<T> {

    protected String buildCantBeNullEmptyOrBlankError(@NonNull final String attributeName) {
        return wrapWithColon(attributeName) + " can't be null, empty or blank";
    }

    protected String buildCantExceedMaxLengthError(@NonNull final String attributeName,
                                                   final int maxLength) {
        return wrapWithColon(attributeName) + " length can't exceed " + maxLength;
    }

    private String wrapWithColon(@NonNull final String source) {
        return "'" + source + "'";
    }
}
