package com.hurynovich.data_unit_schema_service.paginator;

import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.Identified;
import com.hurynovich.data_unit_schema_service.paginator.model.GenericPage;
import org.springframework.lang.NonNull;

import java.util.List;

public interface Paginator {

    PaginationParams buildParams(@NonNull Integer pageNumber, @NonNull Integer elementsPerPage);

    <T extends Identified<?>> GenericPage<T> buildPage(@NonNull List<T> elements,
                                                       long totalElementsCount,
                                                       @NonNull PaginationParams params);
}
