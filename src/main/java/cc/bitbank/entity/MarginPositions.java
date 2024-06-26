package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MarginPositions extends Data {
    public Notice notice;
    public Payable payables;
    public Position[] positions;
    @JsonProperty("losscut_threshold")
    public LosscutThreshold losscutThreshold;

    public static class Notice {
        public String what;
        @JsonProperty("occurred_at")
        public Long occurredAt;
        public BigDecimal amount;
        @JsonProperty("due_date_at")
        public Long dueDateAt;
    }

    public static class Payable {
        public BigDecimal amount;
    }

    public static class Position {
        public String pair;
        @JsonProperty("position_side")
        public String positionSide;
        @JsonProperty("open_amount")
        public BigDecimal openAmount;
        public BigDecimal product;
        @JsonProperty("average_price")
        public BigDecimal averagePrice;
        @JsonProperty("unrealized_fee_amount")
        public BigDecimal unrealizedFeeAmount;
        @JsonProperty("unrealized_interest_amount")
        public BigDecimal unrealizedInterestAmount;
    }

    public static class LosscutThreshold {
        public String company;
        public String individual;
    }

    public String toString() {
        return "[MarginPositions] notice " + notice + ", payables " + payables + ", positions " + positions + ", losscutThreshold " + losscutThreshold;
    }
}
