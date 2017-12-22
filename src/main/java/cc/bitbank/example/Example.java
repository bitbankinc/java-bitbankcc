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
import cc.bitbank.entity.Depth;
import cc.bitbank.entity.Order;
import cc.bitbank.entity.Orders;
import cc.bitbank.entity.Ticker;
import cc.bitbank.entity.Transactions;
import cc.bitbank.entity.Withdraw;
import cc.bitbank.entity.enums.CandleType;
import cc.bitbank.entity.enums.CurrencyPair;
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
            Ticker ticker = bb.getTicker(CurrencyPair.BTC_JPY);

            Depth depth = bb.getDepth(CurrencyPair.BTC_JPY);

            Transactions ts = bb.getTransaction(CurrencyPair.BTC_JPY);

            Transactions.Transaction[] ts2 = bb.getTransaction(CurrencyPair.BTC_JPY, "20170410").transactions;

            List<Candlestick.Ohlcvs.Ohlcv> cs = bb.getCandlestick(CurrencyPair.BTC_JPY, CandleType._1DAY, "2017").candlestick[0].getOhlcvList();

            Assets as = bb.getAsset();
            System.out.println(as.assets[0]);
            System.out.println(as.assets[1]);
            System.out.println(as.assets[2]);
            System.out.println(as.assets[3]);

            Order order = bb.getOrder(CurrencyPair.BTC_JPY, 90956209);
            System.out.println(order);

            Order order2 = bb.sendOrder(CurrencyPair.BTC_JPY, BigDecimal.valueOf(10000), BigDecimal.valueOf(0.01), OrderSide.BUY, OrderType.LIMIT);
            System.out.println(order2);

            Order order3 = bb.cancelOrder(CurrencyPair.BTC_JPY, 129781978);
            System.out.println(order3);

            long[] ids = {129830841, 129830734};
            Orders orders = bb.cancelOrders(CurrencyPair.BTC_JPY, ids);
            System.out.println(orders.orders[0]);
            System.out.println(orders.orders[1]);

            long[] ids2 = {90956209, 90951996};
            Orders orders2 = bb.getOrders(CurrencyPair.BTC_JPY, ids2);
            System.out.println(orders2.orders[0]);
            System.out.println(orders2.orders[1]);

			Map<String, Long> option = new HashMap<String, Long>();
            option.put("count", 1L);
            option.put("since", 1490348550380L);
            // Option's parameter can be seen https://docs.bitbank.cc/#!/Order/active_orders
            Orders orders3 = bb.getActiveOrders(CurrencyPair.BTC_JPY, option);
            for(Order o : orders3.orders) {
                System.out.println(o);
            }

            Accounts accounts = bb.getWithdrawalAccounts("btc");
            for(Accounts.Account a : accounts.accounts) {
                System.out.println(a);
            }

            Withdraw w = bb.requestWithdraw("btc", "XXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXX",
                    BigDecimal.valueOf(0.005), "867005", "");
            System.out.println(w);

        } catch (BitbankException e) {
            System.out.println(e.code);
        } catch (Exception e) {
            System.out.println("エラー " + e.getMessage());
            e.printStackTrace();
        }
    }
}
