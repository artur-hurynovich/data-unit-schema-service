package com.hurynovich.data_unit_schema_service.utils;

import org.springframework.http.codec.HttpMessageWriter;
import org.springframework.lang.NonNull;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

public class ServerResponseUtils {

    private static final String MOCK_URL_TEMPLATE = "http://localhost:8080";

    private ServerResponseUtils() {
        throw new AssertionError();
    }

    public static String extractResponseBody(final ServerResponse serverResponse) {
        final MockServerWebExchange exchange = MockServerWebExchange.from(
                MockServerHttpRequest.get(MOCK_URL_TEMPLATE));
        serverResponse.writeTo(exchange, new DebugServerContext()).block();

        return exchange.getResponse().getBodyAsString().block();
    }

    private static class DebugServerContext implements ServerResponse.Context {

        @Override
        @NonNull
        public List<HttpMessageWriter<?>> messageWriters() {
            return HandlerStrategies.withDefaults().messageWriters();
        }

        @Override
        @NonNull
        public List<ViewResolver> viewResolvers() {
            return Collections.emptyList();
        }
    }
}
