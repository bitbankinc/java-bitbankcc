package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by monja on 2023/02/07.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DepositHistory extends Data {
    public Deposit[] deposits;

    public DepositHistory() {}
    public DepositHistory(Deposit[] deposits) {
        this.deposits = deposits;
    }
}
