package com.microhawk.webissshdemo.model.enums;

public enum NameTitle {
    MR("Mr"),
    MS("Ms"),
    MRS("Mrs"),
    MX("Mx"),
    NONE("NONE");

    private final String value;

    NameTitle(String value) {
        this.value = value;
    }

    private String getValue() {
        return this.value;
    }
}
