package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by tanaka on 2017/04/11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Depth extends Data {

    private BigDecimal[][] asks;
    private BigDecimal[][] bids;
    private Date timestamp;

    public Depth() {}

    public Depth(BigDecimal[][] asks, BigDecimal[][] bids, Date timestamp) {
        this.asks = asks;
        this.bids = bids;
        this.timestamp = timestamp;
    }

    public BigDecimal[][] getAsks() {
        return asks;
    }

    public BigDecimal[][] getBids() {
        return bids;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[Depth] " + "ask " + extract(asks) + ", ... bids " + extract(bids) + ", timestamp " + (timestamp == null ? null : timestamp.getTime());
    }

    private <T> T extract(T[][] input) {
        return input == null ? null : input.length == 0 ? null : input[0] == null ? null : input[0].length == 0 ? null : input[0][0];
    }

}
