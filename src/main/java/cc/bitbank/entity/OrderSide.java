package cc.bitbank.entity;

import cc.bitbank.entity.json.OrderSideDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by tanaka on 2017/04/12.
 */

@JsonDeserialize(using = OrderSideDeserializer.class)
public enum OrderSide {
    BUY("buy"),
    SELL("sell");

    private final String side;

    OrderSide(final String side) {
        this.side = side;
    }

    public String getCode() {
        return this.side;
    }
}