package com.hurynovich.data_unit_schema_service.paginator.model;

import com.hurynovich.data_unit_schema_service.model.Identified;

import java.util.List;

public interface GenericPage<T extends Identified<?>> {

    List<T> getElements();

    Long getTotalElementsCount();

    Long getCurrentPageNumber();

    Long getTotalPagesCount();
}
