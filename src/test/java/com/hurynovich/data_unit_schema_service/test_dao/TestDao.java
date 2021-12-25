package com.hurynovich.data_unit_schema_service.test_dao;

import com.hurynovich.data_unit_schema_service.model.Identified;

import java.io.Serializable;

public interface TestDao<T extends Identified<U>, U extends Serializable> {

    T save(T t);

    T findById(U id);

    void deleteAll();
}
