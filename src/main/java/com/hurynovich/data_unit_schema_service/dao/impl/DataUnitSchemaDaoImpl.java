package com.hurynovich.data_unit_schema_service.dao.impl;

import com.hurynovich.data_unit_schema_service.dao.DataUnitSchemaDao;
import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity;
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
public class DataUnitSchemaDaoImpl implements DataUnitSchemaDao {

    private final Mutiny.SessionFactory sessionFactory;

    public DataUnitSchemaDaoImpl(@NonNull final Mutiny.SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Mono<DataUnitSchemaPersistentModel> save(@NonNull final DataUnitSchemaPersistentModel schema) {
        return sessionFactory
                .withTransaction(session -> session.merge(schema))
                .convert()
                .with(UniReactorConverters.toMono());
    }

    @Override
    public Mono<DataUnitSchemaPersistentModel> findById(@NonNull final Long id) {
        return sessionFactory
                .withTransaction(session -> {
                    final CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
                    final CriteriaQuery<DataUnitSchemaEntity> criteriaQuery = criteriaBuilder
                            .createQuery(DataUnitSchemaEntity.class);
                    final Root<DataUnitSchemaEntity> root = criteriaQuery.from(DataUnitSchemaEntity.class);
                    root.fetch("propertySchemas", JoinType.LEFT);
                    criteriaQuery
                            .where(criteriaBuilder.equal(root.join("propertySchemas").get("dataUnitSchemaId"), id))
                            .distinct(true);

                    return session.createQuery(criteriaQuery).getSingleResultOrNull();
                })
                .convert()
                .with(UniReactorConverters.toMono())
                .map(DataUnitSchemaPersistentModel.class::cast);
    }

    @Override
    public Mono<List<DataUnitSchemaPersistentModel>> findAll(@NonNull final PaginationParams params) {
        return sessionFactory
                .withTransaction(session -> {
                    final CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
                    final CriteriaQuery<DataUnitSchemaEntity> criteriaQuery = criteriaBuilder
                            .createQuery(DataUnitSchemaEntity.class);
                    final Root<DataUnitSchemaEntity> root = criteriaQuery.from(DataUnitSchemaEntity.class);
                    criteriaQuery.select(root);

                    return session.createQuery(criteriaQuery)
                            .setFirstResult(params.offset())
                            .setMaxResults(params.limit())
                            .getResultList();
                })
                .convert()
                .with(UniReactorConverters.toMono())
                .map(schemas -> schemas.stream()
                        .map(DataUnitSchemaPersistentModel.class::cast)
                        .collect(Collectors.toList()));
    }

    @Override
    public Mono<Void> delete(@NonNull final DataUnitSchemaPersistentModel schema) {
        return sessionFactory
                .withTransaction(session -> session.find(DataUnitSchemaEntity.class, schema.getId())
                        .onItem()
                        .ifNotNull()
                        .transformToUni(session::remove))
                .convert()
                .with(UniReactorConverters.toMono());
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
