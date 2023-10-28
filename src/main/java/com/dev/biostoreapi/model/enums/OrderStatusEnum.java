package com.dev.biostoreapi.model.enums;

public enum OrderStatusEnum {

    PENDING("Pending"),
    SHIPPED("Shipped"),
    DELIVERED("Delivered");

    private final String displayName;

    OrderStatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
