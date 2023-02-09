package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by monja on 2023/02/07.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WithdrawalHistory extends Data {
    public Withdraw[] withdrawals;

    public WithdrawalHistory() {}
    public WithdrawalHistory(Withdraw[] withdrawals) {
        this.withdrawals = withdrawals;
    }
}
