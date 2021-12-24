package com.hurynovich.data_unit_schema_service.model;

import java.io.Serializable;

public interface Identified<T extends Serializable> {

    T getId();
}
