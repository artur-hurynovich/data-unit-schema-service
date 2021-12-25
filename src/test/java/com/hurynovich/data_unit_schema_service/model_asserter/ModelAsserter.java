package com.hurynovich.data_unit_schema_service.model_asserter;

import com.hurynovich.data_unit_schema_service.model.Identified;

public interface ModelAsserter<T extends Identified<?>> {

    void assertEquals(T expected, T actual, String... ignoreProperties);
}
