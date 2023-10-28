package com.dev.biostoreapi.model.enums;

public enum StatusEnum {

    PENDING("Pending"),
    ACCEPTED("Accepted"),
    DECLINED("Declined");

    private final String displayName;

    StatusEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }


}
