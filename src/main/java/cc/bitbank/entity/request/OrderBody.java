package cc.bitbank.entity.request;

import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.entity.enums.OrderSide;
import cc.bitbank.entity.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;

/**
 * Created by tanaka on 2017/04/12.
 */
public class OrderBody {
    public String pair;
    public String amount;
    public BigDecimal price;
    public String side;
    public String type;
    @JsonProperty("post_only")
    public boolean postOnly;

    public OrderBody(CurrencyPair pair, BigDecimal amount, BigDecimal price, OrderSide side, OrderType type,
            boolean postOnly) {
        this.pair = pair.getCode();
        this.amount = amount.toString();
        this.price = price;
        this.side = side.getCode();
        this.type = type.getCode();
        this.postOnly = postOnly;
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
