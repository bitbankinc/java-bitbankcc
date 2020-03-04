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
        return "[Depth] " + "ask " + this.asks[0][0] + ", ... bids " + this.bids[0][0] + ", timestamp " + this.timestamp.getTime();
    }
}
