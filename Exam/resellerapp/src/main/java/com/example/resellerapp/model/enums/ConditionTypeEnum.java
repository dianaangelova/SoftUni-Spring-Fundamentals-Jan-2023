package com.example.resellerapp.model.enums;
public enum ConditionTypeEnum {
    EXCELLENT ("Excellent", "In perfect condition"),
    GOOD ("Good", "Some signs of wear and tear or minor defects"),
    ACCEPTABLE ("Acceptable", "The item is fairly worn but continues to function properly");
    public final String label;
    public final String description;
    ConditionTypeEnum(String label, String description) {
        this.label = label;
        this.description = description;
    }
}
