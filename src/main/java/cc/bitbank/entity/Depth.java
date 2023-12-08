package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;


/**
 * Created by tanaka on 2017/04/11.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Depth extends Data {

    private BigDecimal[][] asks;
    private BigDecimal[][] bids;
    @JsonProperty("asks_over")
    private String asksOver;
    @JsonProperty("bids_under")
    private String bidsUnder;
    @JsonProperty("asks_under")
    private String asksUnder;
    @JsonProperty("bids_over")
    private String bidsOver;
    private Date timestamp;
    private String sequenceId;

    public Depth() {}

    public Depth(BigDecimal[][] asks, BigDecimal[][] bids, String asksOver, String bidsUnder, String asksUnder, String bidsOver, Date timestamp, String sequenceId) {
        this.asks = asks;
        this.bids = bids;
        this.asksOver = asksOver;
        this.bidsUnder = bidsUnder;
        this.asksUnder = asksUnder;
        this.bidsOver = bidsOver;
        this.timestamp = timestamp;
        this.sequenceId = sequenceId;
    }

    public BigDecimal[][] getAsks() {
        return asks;
    }

    public BigDecimal[][] getBids() {
        return bids;
    }

    public String getAsksOver() {
        return asksOver;
    }

    public String getBidsUnder() {
        return bidsUnder;
    }

    public String getAsksUnder() {
        return asksUnder;
    }

    public String getBidsOver() {
        return bidsOver;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getSequenceId() {
        return sequenceId;
    }

    @Override
    public String toString() {
        return "[Depth] " +
                "asks " + extract(asks) + "... " +
                "bids " + extract(bids) + "... " +
                "asksOver " + asksOver + " " +
                "bidsUnder " + bidsUnder + " " +
                "asksUnder" + asksUnder + " " +
                "bidsOver" + bidsOver + " " +
                "timestamp" + timestamp + " " +
                "sequenceId" + sequenceId;
    }

    private <T> T extract(T[][] input) {
        return input == null ? null : input.length == 0 ? null : input[0] == null ? null : input[0].length == 0 ? null : input[0][0];
    }

}
