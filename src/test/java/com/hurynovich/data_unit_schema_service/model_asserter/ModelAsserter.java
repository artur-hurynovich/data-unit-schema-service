package com.hurynovich.data_unit_schema_service.model_asserter;

import com.hurynovich.data_unit_schema_service.model.ApiModel;
import com.hurynovich.data_unit_schema_service.model.PersistentModel;
import com.hurynovich.data_unit_schema_service.model.ServiceModel;

public interface ModelAsserter<A extends ApiModel<?>, S extends ServiceModel<?>, P extends PersistentModel<?>> {

    void assertEquals(P expected, P actual, String... ignoreProperties);

    void assertEquals(S expected, S actual, String... ignoreProperties);

    void assertEquals(A expected, S actual, String... ignoreProperties);

    void assertEquals(S expected, A actual, String... ignoreProperties);

    void assertEquals(S expected, P actual, String... ignoreProperties);

    void assertEquals(P expected, S actual, String... ignoreProperties);
}
