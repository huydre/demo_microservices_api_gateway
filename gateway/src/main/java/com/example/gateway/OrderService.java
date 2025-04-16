package com.example.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrderService {

    private final WebClient client;

    public OrderService(OrderDestinations destinations, WebClient.Builder builder) {
        this.client = builder.baseUrl(destinations.getOrderServiceUrl()).build();
    }

    public Mono<JsonNode> findOrderById(String orderId) {
        return client.get()
                .uri("/orders/{id}", orderId)
                .retrieve()
                .bodyToMono(JsonNode.class);
    }
}
