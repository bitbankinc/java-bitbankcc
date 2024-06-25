package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TradeHistory extends Data {
    public Trade[] trades;

    public TradeHistory() {}
    public TradeHistory(Trade[] trades) {
        this.trades = trades;
    }
}
