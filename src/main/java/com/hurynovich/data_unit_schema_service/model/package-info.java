@GenericGenerator(
        name = "data_unit_property_schema_id_generator",
        strategy = "enhanced-sequence",
        parameters = {
                @Parameter(
                        name = "sequence_name",
                        value = "data_unit_property_schema_id_seq"
                ),
                @Parameter(
                        name = "initial_value",
                        value = "1"
                ),
                @Parameter(
                        name = "increment_size",
                        value = "1"
                )
        }
)

@GenericGenerator(
        name = "data_unit_schema_id_generator",
        strategy = "enhanced-sequence",
        parameters = {
                @Parameter(
                        name = "sequence_name",
                        value = "data_unit_schema_id_seq"
                ),
                @Parameter(
                        name = "initial_value",
                        value = "1"
                ),
                @Parameter(
                        name = "increment_size",
                        value = "1"
                )
        }
)
package com.hurynovich.data_unit_schema_service.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

