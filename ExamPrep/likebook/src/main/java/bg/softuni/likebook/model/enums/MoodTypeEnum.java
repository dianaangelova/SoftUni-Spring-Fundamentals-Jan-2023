package bg.softuni.likebook.model.enums;

public enum MoodTypeEnum {

    HAPPY("Happy"),
    SAD("Sad"),
    INSPIRED("Inspired");

    public final String label;

    MoodTypeEnum(String label) {
        this.label = label;
    }
}
