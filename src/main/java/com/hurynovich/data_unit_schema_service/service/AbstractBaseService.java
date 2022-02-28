package com.hurynovich.data_unit_schema_service.service;

import com.hurynovich.data_unit_schema_service.converter.ServiceConverter;
import com.hurynovich.data_unit_schema_service.dao.BaseDao;
import com.hurynovich.data_unit_schema_service.model.PersistentModel;
import com.hurynovich.data_unit_schema_service.model.ServiceModel;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public abstract class AbstractBaseService<T extends ServiceModel<I>, U extends PersistentModel<I>, I extends Serializable>
        implements BaseService<T, I> {

    private final BaseDao<U, I> dao;

    private final ServiceConverter<T, U> converter;

    protected AbstractBaseService(@NonNull final BaseDao<U, I> dao, @NonNull final ServiceConverter<T, U> converter) {
        this.dao = dao;
        this.converter = converter;
    }

    @Override
    public Mono<T> save(@NonNull final T t) {
        return dao.save(converter.convert(t))
                .flatMap(s -> Mono.justOrEmpty(converter.convert(s)));
    }

    @Override
    public Mono<T> findById(@NonNull final I id) {
        return dao.findById(id)
                .flatMap(schema -> Mono.justOrEmpty(converter.convert(schema)));
    }

    @Override
    public Mono<T> deleteById(@NonNull final I id) {
        return dao.deleteById(id)
                .flatMap(schema -> Mono.justOrEmpty(converter.convert(schema)));
    }
}
