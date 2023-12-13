package cc.bitbank.entity.enums;

public enum Mode {
    NONE("NONE"),
    CIRCUIT_BREAK("CIRCUIT_BREAK"),
    FULL_RANGE_CIRCUIT_BREAK("FULL_RANGE_CIRCUIT_BREAK"),
    RESUMPTION("RESUMPTION"),
    LISTING("LISTING");

    private final String mode;

    Mode(final String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return this.mode;
    }
}