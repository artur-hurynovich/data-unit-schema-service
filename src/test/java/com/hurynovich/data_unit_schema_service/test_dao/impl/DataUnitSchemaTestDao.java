package com.hurynovich.data_unit_schema_service.test_dao.impl;

import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaEntity;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaPersistentModel;
import com.hurynovich.data_unit_schema_service.test_dao.TestDao;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.transaction.Transactional;
import java.util.Map;

@Repository
@Transactional
class DataUnitSchemaTestDao implements TestDao<DataUnitSchemaPersistentModel, Long> {

    private static final String ENTITY_GRAPH_KEY = "javax.persistence.fetchgraph";

    private final EntityManager entityManager;

    public DataUnitSchemaTestDao(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public DataUnitSchemaPersistentModel save(final DataUnitSchemaPersistentModel schema) {
        return entityManager.merge(schema);
    }

    @Override
    public DataUnitSchemaPersistentModel findById(final Long id) {
        final EntityGraph<DataUnitSchemaEntity> entityGraph = entityManager.
                createEntityGraph(DataUnitSchemaEntity.class);
        entityGraph.addAttributeNodes("propertySchemas");

        return entityManager.find(DataUnitSchemaEntity.class, id, Map.of(ENTITY_GRAPH_KEY, entityGraph));
    }

    @Override
    public void deleteAll() {
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaDelete<DataUnitSchemaEntity> criteriaQuery = criteriaBuilder
                .createCriteriaDelete(DataUnitSchemaEntity.class);
        criteriaQuery.from(DataUnitSchemaEntity.class);

        entityManager.createQuery(criteriaQuery).executeUpdate();
    }
}
