package com.hurynovich.data_unit_schema_service.service;

import com.hurynovich.data_unit_schema_service.model.ServiceModel;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public interface BaseService<T extends ServiceModel<I>, I extends Serializable> {

    Mono<T> save(@NonNull T t);

    Mono<T> findById(@NonNull I id);

    Mono<T> deleteById(@NonNull I id);
}
