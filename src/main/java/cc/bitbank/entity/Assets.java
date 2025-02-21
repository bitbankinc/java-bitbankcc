package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * Created by tanaka on 2017/04/12.
 */
public class Assets extends Data {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Asset {
        public String asset;
        @JsonProperty("amount_precision")
        public int amountPrecision;
        @JsonProperty("onhand_amount")
        public BigDecimal onhandAmount;
        @JsonProperty("locked_amount")
        public BigDecimal lockedAmount;
        @JsonProperty("free_amount")
        public BigDecimal freeAmount;
        @JsonProperty("withdrawing_amount")
        public BigDecimal withdrawingAmount;
        @JsonProperty("withdrawal_fee")
        public Object withdrawalFee;
        @JsonProperty("explorer")
        public String explorer;
        @JsonProperty("collateral_ratio")
        public BigDecimal collateralRatio;

        public String toString() {
            return "[Assets] asset " + asset + ", onhand_amount " + onhandAmount;
        }
    }

    public Asset[] assets;

    public Assets(){}
    public Assets(Asset[] a) { this.assets = a; }
}
