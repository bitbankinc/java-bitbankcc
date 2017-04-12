package cc.bitbank;

import cc.bitbank.entity.Assets;
import cc.bitbank.entity.Order;
import cc.bitbank.entity.enums.CurrencyPair;
import cc.bitbank.exception.BitbankException;

import java.util.ResourceBundle;


/**
 * Created by tanaka on 2017/04/11.
 */
public class Example {
    public static void main(String args[]) {
        ResourceBundle rb = ResourceBundle.getBundle("example");

        Bitbankcc bb = new Bitbankcc();
        bb.setKey(rb.getString("key"), rb.getString("secret"));

        try {
//            bb.getTicker(CurrencyPair.BTC_JPY);
//            bb.getDepth(CurrencyPair.BTC_JPY);
//            bb.getTransaction(CurrencyPair.BTC_JPY).transactions[0].price
//            bb.getTransaction(CurrencyPair.BTC_JPY, "20170410").transactions[0]
//            bb.getCandlestick(CurrencyPair.BTC_JPY, CandleType._1DAY, "2017").candlestick[0].getOhlcvList()

            Assets as = bb.getAsset();
            System.out.println(as.assets[0]);

            Order order = bb.getOrder(CurrencyPair.BTC_JPY, 90956209);
            System.out.println(order);



//            System.out.println(
//                    bb.getCandlestick(CurrencyPair.BTC_JPY, CandleType._1DAY, "2017").candlestick[0].getOhlcvList()
//            );
        } catch (BitbankException e) {
            System.out.println(e.code);
        } catch (Exception e) {
            System.out.println("エラー " + e.getMessage());
            e.printStackTrace();
        }
    }
}
