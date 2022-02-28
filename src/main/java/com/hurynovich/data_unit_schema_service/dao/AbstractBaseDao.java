package com.hurynovich.data_unit_schema_service.dao;

import com.hurynovich.data_unit_schema_service.model.PersistentModel;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.lang.NonNull;
import reactor.core.publisher.Mono;

import java.io.Serializable;

public abstract class AbstractBaseDao<T extends PersistentModel<I>, I extends Serializable> implements BaseDao<T, I> {

    private static final String ID_PARAM = "id";

    private final Class<? extends T> modelClass;

    protected final ReactiveMongoTemplate template;

    public AbstractBaseDao(final Class<? extends T> modelClass, final ReactiveMongoTemplate template) {
        this.modelClass = modelClass;
        this.template = template;
    }

    @Override
    public Mono<T> save(@NonNull final T t) {
        return template.save(t);
    }

    @Override
    public Mono<T> findById(@NonNull final I id) {
        return template
                .findById(id, modelClass)
                .flatMap(t -> Mono.justOrEmpty((T) t));
    }

    @Override
    public Mono<T> deleteById(@NonNull final I id) {
        final Query query = new Query()
                .addCriteria(Criteria.where(ID_PARAM).is(id));

        return template.findAndRemove(query, modelClass)
                .flatMap(t -> Mono.justOrEmpty((T) t));
    }

    @Override
    public Mono<Long> count() {
        return template.count(new Query(), modelClass);
    }
}
