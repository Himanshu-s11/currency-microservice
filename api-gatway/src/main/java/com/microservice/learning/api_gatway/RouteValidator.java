package com.microservice.learning.api_gatway;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.function.Predicate;
import java.util.*;
@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
        "/auth/register",
            "/auth/token",
            "/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured() {
        return request -> openApiEndpoints.stream()
                .noneMatch(uri -> request.getURI().getPath().contains(uri));
    }
}
