package cc.bitbank.entity.enums;

import cc.bitbank.deserializer.OrderSideDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * Created by tanaka on 2017/04/12.
 */

@JsonDeserialize(using = OrderSideDeserializer.class)
public enum PositionSide {
    LONG("long"),
    SHORT("short");

    private final String side;

    PositionSide(final String side) {
        this.side = side;
    }

    public String getCode() {
        return this.side;
    }
}