package com.hurynovich.data_unit_schema_service.dao.impl;

import com.hurynovich.data_unit_schema_service.dao.DataUnitSchemaDao;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_property_schema.DataUnitPropertySchemaEntity_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity_;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import io.smallrye.mutiny.converters.uni.UniReactorConverters;
import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Repository
class DataUnitSchemaMySQLDaoImpl implements DataUnitSchemaDao {

    private final Mutiny.SessionFactory sessionFactory;

    public DataUnitSchemaMySQLDaoImpl(@NonNull final Mutiny.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Mono<DataUnitSchemaPersistentModel> save(@NonNull final Mono<DataUnitSchemaPersistentModel> schema) {
        return schema.flatMap(s ->
                sessionFactory
                        .withTransaction(session -> session.merge(s))
                        .convert()
                        .with(UniReactorConverters.toMono()));
    }

    @Override
    public Mono<DataUnitSchemaPersistentModel> findById(@NonNull final Mono<Long> id) {
        return id.flatMap(i ->
                sessionFactory
                        .withTransaction(session -> {
                            final CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
                            final CriteriaQuery<DataUnitSchemaEntity> criteriaQuery = criteriaBuilder
                                    .createQuery(DataUnitSchemaEntity.class);
                            final Root<DataUnitSchemaEntity> root = criteriaQuery.from(DataUnitSchemaEntity.class);
                            root.fetch(DataUnitSchemaEntity_.PROPERTY_SCHEMAS, JoinType.LEFT);
                            criteriaQuery
                                    .where(criteriaBuilder.equal(
                                            root.join(DataUnitSchemaEntity_.PROPERTY_SCHEMAS)
                                                    .get(DataUnitPropertySchemaEntity_.DATA_UNIT_SCHEMA_ID), i))
                                    .distinct(true);

                            return session.createQuery(criteriaQuery).getSingleResultOrNull();
                        })
                        .convert()
                        .with(UniReactorConverters.toMono())
                        .flatMap(schema -> Mono.justOrEmpty((DataUnitSchemaPersistentModel) schema)));
    }

    @Override
    public Mono<List<DataUnitSchemaPersistentModel>> findAll(@NonNull final Mono<PaginationParams> params) {
        return params.flatMap(p ->
                sessionFactory
                        .withTransaction(session -> {
                            final CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
                            final CriteriaQuery<DataUnitSchemaEntity> criteriaQuery = criteriaBuilder
                                    .createQuery(DataUnitSchemaEntity.class);
                            final Root<DataUnitSchemaEntity> root = criteriaQuery.from(DataUnitSchemaEntity.class);
                            criteriaQuery.select(root);

                            return session.createQuery(criteriaQuery)
                                    .setFirstResult(p.offset())
                                    .setMaxResults(p.limit())
                                    .getResultList();
                        })
                        .convert()
                        .with(UniReactorConverters.toMono())
                        .flatMap(schemas -> Mono.just(schemas.stream()
                                .map(DataUnitSchemaPersistentModel.class::cast)
                                .collect(Collectors.toList()))));
    }

    @Override
    public Mono<Void> deleteById(@NonNull final Mono<Long> id) {
        return id.flatMap(i ->
                sessionFactory
                        .withTransaction(session -> session.find(DataUnitSchemaEntity.class, i)
                                .onItem()
                                .ifNotNull()
                                .transformToUni(session::remove))
                        .convert()
                        .with(UniReactorConverters.toMono()));
    }

    @Override
    public Mono<Long> count() {
        return sessionFactory
                .withTransaction(session -> {
                    final CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
                    final CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
                    final Root<DataUnitSchemaEntity> root = criteriaQuery.from(DataUnitSchemaEntity.class);
                    criteriaQuery.select(criteriaBuilder.count(root));

                    return session.createQuery(criteriaQuery).getSingleResult();
                })
                .convert()
                .with(UniReactorConverters.toMono());
    }
}
