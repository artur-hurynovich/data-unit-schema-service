package com.hurynovich.data_unit_schema_service.dao.model;

import org.springframework.lang.NonNull;

public record PaginationParams(@NonNull Integer offset, @NonNull Integer limit) {

}
