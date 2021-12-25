package com.hurynovich.data_unit_schema_service.utils;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class MassProcessingUtils {

    private MassProcessingUtils() {
        throw new AssertionError();
    }

    public static <T, U> List<U> processQuietly(final @Nullable List<T> elements,
                                                final @NonNull Function<T, U> processingFunction) {
        final List<U> processedElements = new ArrayList<>();
        if (elements != null) {
            processedElements.addAll(elements.stream()
                    .filter(Objects::nonNull)
                    .map(processingFunction)
                    .filter(Objects::nonNull)
                    .toList());
        }

        return processedElements;
    }
}
