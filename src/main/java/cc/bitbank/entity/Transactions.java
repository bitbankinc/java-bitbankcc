package cc.bitbank.entity;

import cc.bitbank.entity.enums.OrderSide;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by tanaka on 2017/04/12.
 */
public class Transactions extends Data {

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Transaction {
        @JsonProperty("transaction_id")
        private long transactionId;

        private OrderSide side;

        private String price;

        private String amount;

        @JsonProperty("executed_at")
        private Date executedAt;

        public String toString() {
            return "[Transaction] transaction_id " + transactionId + ", side " + side.getCode() + ", price " + price + ", amount " +
                    amount + ", executed_at " + executedAt.toString();
        }

        public Long getTransactionId() {
            return transactionId;
        }

        public OrderSide getSide() {
            return side;
        }

        public BigDecimal getPrice() {
            return new BigDecimal(price);
        }

        public BigDecimal getAmount() {
            return new BigDecimal(amount);
        }

        public Date getExecutedAt() {
            return executedAt;
        }
    }

    public Transaction[] transactions;

    public Transactions () {}
    public Transactions (Transaction[] transactions) {
        this.transactions = transactions;
    }

    public Transaction[] getTransactions() {
        return transactions;
    }
}
