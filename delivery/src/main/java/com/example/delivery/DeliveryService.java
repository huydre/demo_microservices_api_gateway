package com.example.delivery;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliveryService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("deliveries.json");

    public List<Delivery> getAllDeliveries() {
        try {
            if (!file.exists() || file.length() == 0) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<List<Delivery>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public void saveDelivery(Delivery newDelivery) {
        try {
            List<Delivery> deliveries = getAllDeliveries();
            deliveries.add(newDelivery);
            mapper.writeValue(file, deliveries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Delivery findDeliveryByOrderId(String orderId) {
        return getAllDeliveries().stream()
                .filter(d -> orderId.equals(d.getOrderId()))
                .findFirst()
                .orElse(null);
    }
}
