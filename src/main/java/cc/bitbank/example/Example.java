package cc.bitbank.example;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.Accounts;
import cc.bitbank.entity.Assets;
import cc.bitbank.entity.Candlestick;
import cc.bitbank.entity.CircuitBreakInfo;
import cc.bitbank.entity.Deposit;
import cc.bitbank.entity.DepositHistory;
import cc.bitbank.entity.Depth;
import cc.bitbank.entity.MarginPositions;
import cc.bitbank.entity.Order;
import cc.bitbank.entity.Orders;
import cc.bitbank.entity.Subscribe;
import cc.bitbank.entity.Ticker;
import cc.bitbank.entity.Trade;
import cc.bitbank.entity.TradeHistory;
import cc.bitbank.entity.Transactions;
import cc.bitbank.entity.Withdraw;
import cc.bitbank.entity.WithdrawalHistory;
import cc.bitbank.entity.enums.CandleType;
import cc.bitbank.entity.enums.OrderSide;
import cc.bitbank.entity.enums.OrderType;
import cc.bitbank.exception.BitbankException;


/**
 * Created by tanaka on 2017/04/11.
 */
public class Example {
    public static void main(String args[]) {
        ResourceBundle rb = ResourceBundle.getBundle("example");
        Bitbankcc bb = new Bitbankcc();
        bb.setKey(rb.getString("key"), rb.getString("secret"));

        try {
            Ticker ticker = bb.getTicker("btc_jpy");
            System.out.println(ticker.toString());

            Depth depth = bb.getDepth("btc_jpy");
            System.out.println(depth.toString());

            Transactions ts = bb.getTransaction("btc_jpy");
            System.out.println(ts.toString());

            Transactions.Transaction[] ts2 = bb.getTransaction("btc_jpy", "20170410").transactions;
            System.out.println(ts2.toString());

            List<Candlestick.Ohlcvs.Ohlcv> cs = bb.getCandlestick("btc_jpy", CandleType._1DAY, "2017").candlestick[0].getOhlcvList();
            System.out.println(cs.toString());

            CircuitBreakInfo cbi = bb.getCircuitBreakInfo("btc_jpy");
            System.out.println(cbi.toString());

            Assets as = bb.getAsset();
            System.out.println(as.assets[0]);
            System.out.println(as.assets[1]);
            System.out.println(as.assets[2]);
            System.out.println(as.assets[3]);

            Order order = bb.getOrder("btc_jpy", 90956209);
            System.out.println(order);

            Order order2 = bb.sendOrder("btc_jpy", BigDecimal.valueOf(10000), BigDecimal.valueOf(0.01), OrderSide.BUY, OrderType.LIMIT);
            System.out.println(order2);

            Order order3 = bb.cancelOrder("btc_jpy", 129781978);
            System.out.println(order3);

            long[] ids = {129830841, 129830734};
            Orders orders = bb.cancelOrders("btc_jpy", ids);
            System.out.println(orders.orders[0]);
            System.out.println(orders.orders[1]);

            long[] ids2 = {90956209, 90951996};
            Orders orders2 = bb.getOrders("btc_jpy", ids2);
            System.out.println(orders2.orders[0]);
            System.out.println(orders2.orders[1]);

			Map<String, Long> option = new HashMap<String, Long>();
            option.put("count", 1L);
            option.put("since", 1490348550380L);
            // Option's parameter can be seen https://docs.bitbank.cc/#!/Order/active_orders
            Orders orders3 = bb.getActiveOrders("btc_jpy", option);
            for(Order o : orders3.orders) {
                System.out.println(o);
            }

            MarginPositions positions = bb.getMarginPositions();
            System.out.println(positions);

            Map<String, String> option2 = new HashMap<String, String>();
            option2.put("count", "1");
            option2.put("since", "1490348550380");
            DepositHistory history = bb.getDepositHistory("btc", option2);
            for(Deposit d : history.deposits) {
                System.out.println(d);
            }

            Map<String, String> option3 = new HashMap<String, String>();
            option3.put("count", "1");
            option3.put("since", "1696410137317");
            TradeHistory history2 = bb.getTradeHistory("qtum_jpy", option3);
            for(Trade d : history2.trades) {
                System.out.println(d);
            }

            Accounts accounts = bb.getWithdrawalAccounts("btc");
            for(Accounts.Account a : accounts.accounts) {
                System.out.println(a);
            }

            Withdraw w = bb.requestWithdraw("btc", "XXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXX",
                    BigDecimal.valueOf(0.005), "867005", "");
            System.out.println(w);

            Map<String, String> option4 = new HashMap<String, String>();
            option4.put("count", "1");
            option4.put("since", "1490348550380");
            WithdrawalHistory history3 = bb.getWithdrawalHistory("btc", option4);
            for(Withdraw w2 : history3.withdrawals) {
                System.out.println(w2);
            }

            Subscribe sub = bb.getSubscribe();
            System.out.println(sub);
        } catch (BitbankException e) {
            System.out.println(e.code);
        } catch (Exception e) {
            System.out.println("エラー " + e.getMessage());
            e.printStackTrace();
        }
    }
}
