package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import cc.bitbank.entity.enums.OrderSide;
import cc.bitbank.entity.enums.OrderType;
import cc.bitbank.entity.enums.PositionSide;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by monja on 2023/02/07.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Trade extends Data {

    @JsonProperty("trade_id")
    public long tradeId;
    public String pair;
    @JsonProperty("order_id")
    public long orderId;
    public OrderSide side;
    public OrderType type;
    public BigDecimal amount;
    public BigDecimal price;
    @JsonProperty("maker_taker")
    public String makerTaker;
    @JsonProperty("position_side")
    public PositionSide positionSide;
    @JsonProperty("fee_amount_base")
    public BigDecimal feeAmountBase;
    @JsonProperty("fee_amount_quote")
    public BigDecimal feeAmountQuote;
    @JsonProperty("fee_occurred_amount_quote")
    public BigDecimal feeOccurredAmountQuote;
    public BigDecimal interest;
    @JsonProperty("profit_loss")
    public BigDecimal profitLoss;
    @JsonProperty("executed_at")
    public Date executedAt;


    public String toString() {
        return "[Trade] tradeId " + tradeId + ", pair " + pair + ", orderId " + orderId + ", side " + side + ", type " + type +
                ", amount " + amount + ", price " + price + ", makerTaker " + makerTaker + ", positionSide " + positionSide +
                ", feeAmountBase " + feeAmountBase + ", feeAmountQuote " + feeAmountQuote + ", feeOccurredAmountQuote " + feeOccurredAmountQuote +
                ", interest " + interest + ", profitLoss " + profitLoss + ", executedAt " + executedAt;
    }
}
