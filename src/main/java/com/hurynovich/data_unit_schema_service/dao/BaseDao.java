package com.hurynovich.data_unit_schema_service.dao;

import com.hurynovich.data_unit_schema_service.model.PersistentModel;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public interface BaseDao<T extends PersistentModel<I>, I extends Serializable> {

    Mono<T> save(@NonNull T t);

    Mono<T> findById(@NonNull I id);

    Mono<T> deleteById(@NonNull I id);

    Mono<Long> count();
}
