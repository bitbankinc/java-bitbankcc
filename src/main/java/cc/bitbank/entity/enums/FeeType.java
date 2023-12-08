package cc.bitbank.entity.enums;

public enum FeeType {
    NORMAL("NORMAL"),
    SELL_MAKER("SELL_MAKER"),
    BUY_MAKER("BUY_MAKER"),
    DYNAMIC("DYNAMIC");

    private final String type;

    FeeType(final String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }
}