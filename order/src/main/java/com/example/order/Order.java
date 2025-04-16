package com.example.order;

public class Order {
    private String id;
    private String item;
    private int quantity;

    public Order() {}

    public Order(String item, String id, int quantity) {
        this.item = item;
        this.id = id;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
