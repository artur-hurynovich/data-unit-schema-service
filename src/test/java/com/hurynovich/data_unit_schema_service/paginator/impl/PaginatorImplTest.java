package com.hurynovich.data_unit_schema_service.paginator.impl;

import com.hurynovich.data_unit_schema_service.dao.model.PaginationParams;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModel;
import com.hurynovich.data_unit_schema_service.model.data_unit_schema.DataUnitSchemaApiModelImpl;
import com.hurynovich.data_unit_schema_service.paginator.Paginator;
import com.hurynovich.data_unit_schema_service.paginator.model.GenericPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.LongStream;

class PaginatorImplTest {

    private static final int ELEMENTS_PER_PAGE = 5;

    private final Paginator paginator = new PaginatorImpl();

    @Test
    void buildParamsTest1() {
        final PaginationParams params = paginator.buildParams(1, ELEMENTS_PER_PAGE);

        Assertions.assertEquals(0, params.offset());
        Assertions.assertEquals(5, params.limit());
    }

    @Test
    void buildParamsTest2() {
        final PaginationParams params = paginator.buildParams(5, ELEMENTS_PER_PAGE);

        Assertions.assertEquals(20, params.offset());
        Assertions.assertEquals(5, params.limit());
    }

    @Test
    void buildPageTest1() {
        final long totalElementsCount = 27L;
        final GenericPage<DataUnitSchemaApiModel> page = paginator.buildPage(buildMockElements(totalElementsCount),
                totalElementsCount, new PaginationParams(0, ELEMENTS_PER_PAGE));

        Assertions.assertEquals(totalElementsCount, page.getTotalElementsCount());
        Assertions.assertEquals(1, page.getCurrentPageNumber());
        Assertions.assertEquals(6, page.getTotalPagesCount());
    }

    @Test
    void buildPageTest2() {
        final long totalElementsCount = 27L;
        final GenericPage<DataUnitSchemaApiModel> page = paginator.buildPage(buildMockElements(totalElementsCount),
                totalElementsCount, new PaginationParams(10, ELEMENTS_PER_PAGE));

        Assertions.assertEquals(totalElementsCount, page.getTotalElementsCount());
        Assertions.assertEquals(3, page.getCurrentPageNumber());
        Assertions.assertEquals(6, page.getTotalPagesCount());
    }

    @Test
    void buildPageTest3() {
        final long totalElementsCount = 27L;
        final GenericPage<DataUnitSchemaApiModel> page = paginator.buildPage(buildMockElements(totalElementsCount),
                totalElementsCount, new PaginationParams(25, ELEMENTS_PER_PAGE));

        Assertions.assertEquals(27L, page.getTotalElementsCount());
        Assertions.assertEquals(6, page.getCurrentPageNumber());
        Assertions.assertEquals(6, page.getTotalPagesCount());
    }

    @Test
    void buildPageTest4() {
        final long totalElementsCount = 3L;
        final GenericPage<DataUnitSchemaApiModel> page = paginator.buildPage(buildMockElements(totalElementsCount),
                totalElementsCount, new PaginationParams(0, ELEMENTS_PER_PAGE));

        Assertions.assertEquals(totalElementsCount, page.getTotalElementsCount());
        Assertions.assertEquals(1, page.getCurrentPageNumber());
        Assertions.assertEquals(1, page.getTotalPagesCount());
    }

    @Test
    void buildPageTest5() {
        final GenericPage<DataUnitSchemaApiModel> page = paginator.buildPage(
                new ArrayList<>(), 0L, new PaginationParams(0, ELEMENTS_PER_PAGE));

        Assertions.assertEquals(0L, page.getTotalElementsCount());
        Assertions.assertEquals(0L, page.getCurrentPageNumber());
        Assertions.assertEquals(0, page.getTotalPagesCount());
    }

    private List<DataUnitSchemaApiModel> buildMockElements(final long totalElementsCount) {
        return LongStream.range(0, totalElementsCount)
                .mapToObj(id -> buildMockElement(String.valueOf(id)))
                .toList();
    }

    private DataUnitSchemaApiModel buildMockElement(final String id) {
        return new DataUnitSchemaApiModelImpl(id, "", List.of());
    }
}
