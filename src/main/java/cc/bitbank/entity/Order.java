package cc.bitbank.entity;

import cc.bitbank.entity.enums.OrderSide;
import cc.bitbank.entity.enums.OrderType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tanaka on 2017/04/12.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Order extends Data {
    @JsonProperty("order_id")
    public long orderId;
    public String pair;
    public OrderSide side;
    public OrderType type;
    @JsonProperty("start_amount")
    public BigDecimal startAmount;
    @JsonProperty("remaining_amount")
    public BigDecimal remainingAmount;
    @JsonProperty("executed_amount")
    public BigDecimal executedAmount;
    public BigDecimal price;
    @JsonProperty("post_only")
    public boolean postOnly;
    @JsonProperty("average_price")
    public BigDecimal averagePrice;
    @JsonProperty("trigger_price")
    public BigDecimal triggerPrice;
    @JsonProperty("ordered_at")
    public Date orderedAt;
    @JsonProperty("expire_at")
    public Date expireAt;
    @JsonProperty("canceled_at")
    public Date canceledAt;
    @JsonProperty("executed_at")
    public Date executedAt;
    @JsonProperty("triggered_at")
    public Date triggeredAt;
    public String status;

    public String toString() {
        return "[Order] orderId " + orderId + ", pair " + pair + ", side " + side + ", type " + type +
                ", remainingAmount " + remainingAmount + ", price " + price + ", status " + status;
    }
}
