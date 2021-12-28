package com.hurynovich.data_unit_schema_service.paginator.impl;

import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.Identified;
import com.hurynovich.data_unit_schema_service.paginator.Paginator;
import com.hurynovich.data_unit_schema_service.paginator.model.GenericPage;
import com.hurynovich.data_unit_schema_service.paginator.model.impl.GenericPageBuilderImpl;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class PaginatorImpl implements Paginator {

    @Override
    public PaginationParams buildParams(@NonNull final Integer pageNumber,
                                        @NonNull final Integer elementsPerPage) {
        return new PaginationParams((pageNumber - 1) * elementsPerPage, elementsPerPage);
    }

    @Override
    public <T extends Identified<?>> GenericPage<T> buildPage(@NonNull final List<T> elements,
                                                              final long totalElementsCount,
                                                              @NonNull final PaginationParams params) {
        final Integer offset = params.offset();
        final Integer limit = params.limit();

        final long currentPageNumber = calculateCurrentPageNumber(elements, offset, limit);
        final long totalPagesCount = calculateTotalPagesCount(totalElementsCount, limit);

        return new GenericPageBuilderImpl<T>()
                .withElements(elements)
                .withTotalElementsCount(totalElementsCount)
                .withCurrentPageNumber(currentPageNumber)
                .withTotalPagesCount(totalPagesCount)
                .build();
    }

    private <T extends Identified<?>> long calculateCurrentPageNumber(@NonNull final List<T> elements,
                                                                      @NonNull final Integer offset,
                                                                      @NonNull final Integer limit) {
        final long currentPageNumber;
        if (elements.size() > 0) {
            currentPageNumber = offset / limit + 1;
        } else {
            currentPageNumber = 0;
        }

        return currentPageNumber;
    }

    private long calculateTotalPagesCount(@NonNull final Long totalElementsCount,
                                          @NonNull final Integer limit) {
        final long totalPagesCount;
        if (totalElementsCount % limit > 0) {
            totalPagesCount = totalElementsCount / limit + 1;
        } else {
            totalPagesCount = totalElementsCount / limit;
        }

        return totalPagesCount;
    }
}
