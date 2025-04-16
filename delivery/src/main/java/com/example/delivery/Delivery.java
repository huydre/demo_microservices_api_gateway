package com.example.delivery;

public class Delivery {
    private String id;
    private String address;
    private String status;
    private String orderId; // ✅ mới thêm

    public Delivery() {}

    public Delivery(String id, String address, String status, String orderId) {
        this.id = id;
        this.address = address;
        this.status = status;
        this.orderId = orderId;
    }

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
}
