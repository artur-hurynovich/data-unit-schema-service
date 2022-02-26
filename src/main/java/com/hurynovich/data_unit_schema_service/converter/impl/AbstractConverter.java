package com.hurynovich.data_unit_schema_service.converter.impl;

import org.modelmapper.ModelMapper;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

public abstract class AbstractConverter {

    private final ModelMapper mapper;

    public AbstractConverter(final ModelMapper mapper) {
        this.mapper = mapper;
    }

    protected <T, U> U convert(@Nullable final T t, @NonNull Class<U> targetClass) {
        final U u;
        if (t != null) {
            u = mapper.map(t, targetClass);
        } else {
            u = null;
        }

        return u;
    }
}
