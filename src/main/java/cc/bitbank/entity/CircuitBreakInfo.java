package cc.bitbank.entity;

import cc.bitbank.entity.enums.FeeType;
import cc.bitbank.entity.enums.Mode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CircuitBreakInfo extends Data {
    public Mode mode;
    @JsonProperty("upper_trigger_price")
    public BigDecimal upperTriggerPrice;
    @JsonProperty("lower_trigger_price")
    public BigDecimal lowerTriggerPrice;
    @JsonProperty("fee_type")
    public FeeType feeType;
    @JsonProperty("estimated_itayose_price")
    public BigDecimal estimatedItayosePrice;
    @JsonProperty("itayose_upper_price")
    public BigDecimal itayoseUpperPrice;
    @JsonProperty("itayose_lower_price")
    public BigDecimal itayoseLowerPrice;
    @JsonProperty("reopen_timestamp")
    public Date reopenTimestamp;
    public Date timestamp;

    public String toString() {
        return "[CircuitBreakInfo] mode " + mode + ", upperTriggerPrice " + upperTriggerPrice + ", lowerTriggerPrice " + lowerTriggerPrice +
                ", feeType " + feeType + ", estimatedItayosePrice " + estimatedItayosePrice + ", itayoseUpperPrice " + itayoseUpperPrice +
                ", itayoseLowerPrice " + itayoseLowerPrice + ", reopenTimestamp " + reopenTimestamp + ", timestamp " + timestamp;
    }
}
