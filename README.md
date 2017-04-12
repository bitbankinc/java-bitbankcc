# java-bitbankcc
Java library for bitbank.cc, Bitcoin exchange.

[https://bitbank.cc/](https://bitbank.cc/)

API document: [https://docs.bitbank.cc/](https://docs.bitbank.cc/)

# install


# How to use
## Public API
```
import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.*;
import cc.bitbank.entity.enums.*;

Bitbankcc bb = new Bitbankcc();


Ticker ticker = bb.getTicker(CurrencyPair.BTC_JPY);
Depth depth = bb.getDepth(CurrencyPair.BTC_JPY);
Transaction[] ts = bb.getTransaction(CurrencyPair.BTC_JPY).transactions;
Transaction[] ts2 = bb.getTransaction(CurrencyPair.BTC_JPY, "20170410").transactions;
List<Ohlcv> ohlcvs = bb.getCandlestick(CurrencyPair.BTC_JPY, CandleType._1DAY, "2017").candlestick[0].getOhlcvList();

```

## Private API
Private api requires API_KEY and API_SECRET.
[https://bitbank.cc/account/api](https://bitbank.cc/account/api)

```
Bitbankcc bb = new Bitbankcc();
bb.setKey("YOUR_API_KEY", "YOUR_API_SECRET");
```

#### Assets
```
Assets as = bb.getAsset();
```

#### Order Info
```
Order order = bb.getOrder(CurrencyPair.BTC_JPY, 90956209);
```