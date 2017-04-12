package cc.bitbank.entity.enums;

/**
 * Created by tanaka on 2017/04/11.
 */

public enum CurrencyPair {
    BTC_JPY("btc_jpy");

    private final String pair;

    CurrencyPair(final String pair) {
        this.pair = pair;
    }

    public String getCode() {
        return this.pair;
    }
}