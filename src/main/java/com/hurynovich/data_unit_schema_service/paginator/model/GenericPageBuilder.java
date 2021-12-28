package com.hurynovich.data_unit_schema_service.paginator.model;

import com.hurynovich.data_unit_schema_service.model.Identified;

import java.util.List;

public interface GenericPageBuilder<T extends Identified<?>> {

    List<T> getElements();

    GenericPageBuilder<T> withElements(List<T> elements);

    Long getTotalElementsCount();

    GenericPageBuilder<T> withTotalElementsCount(Long totalElementsCount);

    Long getCurrentPageNumber();

    GenericPageBuilder<T> withCurrentPageNumber(Long currentPageNumber);

    Long getTotalPagesCount();

    GenericPageBuilder<T> withTotalPagesCount(Long totalPagesCount);

    GenericPage<T> build();
}
