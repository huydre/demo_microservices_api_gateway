package com.example.gateway;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class OrderHandlers {

    private final OrderService orderService;
    private final DeliveryService deliveryService;

    public OrderHandlers(OrderService orderService, DeliveryService deliveryService) {
        this.orderService = orderService;
        this.deliveryService = deliveryService;
    }

    public Mono<ServerResponse> getOrderDetails(ServerRequest request) {
        String orderId = request.pathVariable("orderId");

        Mono<JsonNode> orderMono = orderService.findOrderById(orderId);
        Mono<JsonNode> deliveryMono = deliveryService.findDeliveryByOrderId(orderId);

        return Mono.zip(orderMono, deliveryMono)
                .flatMap(tuple -> {
                    ObjectNode orderCopy = tuple.getT1().deepCopy();
                    ObjectNode delivery = tuple.getT2().deepCopy();
                    delivery.remove("orderId");
                    orderCopy.set("delivery", delivery);

                    ObjectNode result = JsonNodeFactory.instance.objectNode();
                    result.set("order", orderCopy);

                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(result);
                })
                .switchIfEmpty(ServerResponse.notFound().build());
    }
}
