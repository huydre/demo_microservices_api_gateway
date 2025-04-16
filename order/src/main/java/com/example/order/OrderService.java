package com.example.order;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File("orders.json");

    // Đọc toàn bộ danh sách từ file
    public List<Order> getAllOrders() {
        try {
            if (!file.exists() || file.length() == 0) return new ArrayList<>();
            return mapper.readValue(file, new TypeReference<List<Order>>() {});
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // Ghi thêm đơn hàng mới vào file
    public void saveOrder(Order newOrder) {
        try {
            List<Order> orders = getAllOrders(); // đọc hiện có
            orders.add(newOrder);               // thêm order mới
            mapper.writeValue(file, orders);    // ghi đè lại toàn bộ danh sách
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Order findOrderById(String id) {
        return getAllOrders().stream()
                .filter(o -> o.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
