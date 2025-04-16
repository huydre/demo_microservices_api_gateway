package com.example.gateway;

import com.example.gateway.OrderDestinations;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@EnableConfigurationProperties(OrderDestinations.class)
public class OrderConfiguration {

    @Bean
    public RouteLocator orderProxyRouting(RouteLocatorBuilder builder, OrderDestinations destinations) {
        return builder.routes()
                .route("orders", r -> r
                        .path("/orders", "/orders/**")
                        .uri(destinations.getOrderServiceUrl()))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> orderHandlerRouting(OrderHandlers orderHandlers) {
        return RouterFunctions.route(
                RequestPredicates.GET("/orders/{orderId}"),
                orderHandlers::getOrderDetails
        );
    }
}
