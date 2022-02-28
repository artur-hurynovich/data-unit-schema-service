package com.hurynovich.data_unit_schema_service.paginator.model.impl;

import com.hurynovich.data_unit_schema_service.model.Identified;
import com.hurynovich.data_unit_schema_service.paginator.model.GenericPage;
import com.hurynovich.data_unit_schema_service.paginator.model.GenericPageBuilder;
import org.springframework.lang.NonNull;

import java.util.List;

class GenericPageImpl<T extends Identified<?>> implements GenericPage<T> {

    private final List<T> elements;

    private final Long totalElementsCount;

    private final Long currentPageNumber;

    private final Long totalPagesCount;

    public GenericPageImpl(@NonNull final GenericPageBuilder<T> builder) {
        this.elements = builder.getElements();
        this.totalElementsCount = builder.getTotalElementsCount();
        this.currentPageNumber = builder.getCurrentPageNumber();
        this.totalPagesCount = builder.getTotalPagesCount();
    }

    @Override
    public List<T> getElements() {
        return elements;
    }

    @Override
    public Long getTotalElementsCount() {
        return totalElementsCount;
    }

    @Override
    public Long getCurrentPageNumber() {
        return currentPageNumber;
    }

    @Override
    public Long getTotalPagesCount() {
        return totalPagesCount;
    }
}
