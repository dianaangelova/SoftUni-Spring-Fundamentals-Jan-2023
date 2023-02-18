package com.example.shoppinglist.model.enums;

public enum ProductTypeEnum {

    FOOD("Food"),
    DRINK("Drink"),
    HOUSEHOLD("Household"),
    OTHER("Other");

    public final String label;

    ProductTypeEnum(String label) {
        this.label = label;
    }
}
