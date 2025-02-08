package com.microservice.learning.api_gatway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
/*
Client
  |
  v
API Gateway
  |
  v
Eureka (Service Discovery) <--- Used by API Gateway
  |
  v
Currency Conversion (Service)
  |
  v
Currency Exchange (Called via Feign Client, if needed)
  |
  v
Response travels back to Client
 */
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder, AuthenticationFilter authenticationFilter) {
		// Function<PredicateSpec, Buildable<Route>> routeFunction =
		return builder.routes()
				.route(p -> p.path("/currency-exchange/**")
						.filters(f->f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
						.uri("lb://currency-exchange")) // lb -> load balance
				.route(p->p.path("/currency-conversion-feign/**")
						.filters(f->f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
						.uri("lb://currency-conversion")) // lb -> load balance
				.route(p->p.path("/auth/**").uri("lb://auth-service"))
				.build();

	}
}
