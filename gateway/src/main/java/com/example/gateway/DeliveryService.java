package com.example.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DeliveryService {

    private final WebClient client;

    public DeliveryService(OrderDestinations destinations, WebClient.Builder builder) {
        this.client = builder.baseUrl(destinations.getDeliveryServiceUrl()).build();
    }

    public Mono<JsonNode> findDeliveryByOrderId(String orderId) {
        return client.get()
                .uri("/deliveries/order/{orderId}", orderId)
                .retrieve()
                .bodyToMono(JsonNode.class)
                .onErrorResume(e -> Mono.empty());
    }
}
