package com.hurynovich.data_unit_schema_service.converter;

import com.hurynovich.data_unit_schema_service.model.PersistentModel;
import com.hurynovich.data_unit_schema_service.model.ServiceModel;
import org.springframework.lang.Nullable;

public interface ServiceConverter<T extends ServiceModel<?>, U extends PersistentModel<?>> {

    U convert(@Nullable T source);

    T convert(@Nullable U source);
}
