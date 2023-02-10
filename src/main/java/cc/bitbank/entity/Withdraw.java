package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tanaka on 2017/04/13.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Withdraw extends Data {

    public String uuid;
    public String asset;
    @JsonProperty("account_uuid")
    public String accountUuid;
    public BigDecimal amount;
    public BigDecimal fee;

    public String label;
    public String address;
    /** destinationTag is actually Integer or String, depending on asset. */
    @JsonProperty("destination_tag")
    public Object destinationTag;
    public String txid;

    @JsonProperty("bank_name")
    public String bankName;
    @JsonProperty("branch_name")
    public String branchName;
    @JsonProperty("account_type")
    public String accountType;
    @JsonProperty("account_number")
    public String accountNumber;
    @JsonProperty("account_owner")
    public String accountOwner;

    public String status;
    @JsonProperty("requested_at")
    public Date requestedAt;

    public String toString() {
        String addrinfo;
        if (address != null) {
            // asset must be crypto
            addrinfo = ", label " + label + ", address " + address + ", destination_tag " + destinationTag + ", txid "
                    + txid;
        } else if (accountNumber != null) {
            // asset must be fiat
            addrinfo = ", bank_name " + bankName + ", branch_name " + branchName + ", account_type " + accountType
                    + ", account_number " + accountNumber + ", account_owner " + accountOwner;
        } else {
            // ?? avoid addrinfo to be null anyway
            addrinfo = "";
        }

        return "[Withdraw] uuid " + uuid + ", asset " + asset + ", account_uuid " + accountUuid + ", amount " + amount
                + ", fee " + fee + addrinfo + ", status " + status;
    }
}
