package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by monja on 2023/02/07.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Deposit extends Data {

    public String uuid;
    public String asset;
    public BigDecimal amount;
    public String txid;
    public String status;
    @JsonProperty("found_at")
    public Date foundAt;
    @JsonProperty("confirmed_at")
    public Date confirmedAt;

    public String toString() {
        return "[Deposit] uuid " + uuid + ", asset " + asset + ", amount " + amount +
        ", status " + status + ", found_at " + foundAt + ", confirmed_at " + confirmedAt;
    }
}
