package com.hurynovich.data_unit_schema_service.converter;

import com.hurynovich.data_unit_schema_service.model.ApiModel;
import com.hurynovich.data_unit_schema_service.model.ServiceModel;
import org.springframework.lang.Nullable;

public interface ApiConverter<T extends ApiModel<?>, U extends ServiceModel<?>> {

    U convert(@Nullable T source);

    T convert(@Nullable U source);
}
