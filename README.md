# java-bitbankcc
Java library for bitbank.cc, Bitcoin exchange.

[https://bitbank.cc/](https://bitbank.cc/)

API document: [https://docs.bitbank.cc/](https://docs.bitbank.cc/)

# Install
Add the following dependency to your project's pom.xml
```xml
<repositories>
    <repository>
        <id>java-bitbankcc</id>
        <url>https://github.com/bitbankinc/java-bitbankcc/tree/mvn-repo/</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>cc.bitbank</groupId>
        <artifactId>java-bitbankcc</artifactId>
        <version>2.2.0</version>
    </dependency>
</dependencies>
```
Or you can use .jar file here [https://github.com/bitbankinc/java-bitbankcc/releases](https://github.com/bitbankinc/java-bitbankcc/releases)

# Exec Sample

1. prepare src/main/resources/example.properties and write your API key and secret
2. exec `$ mvn clean compile exec:java -Dexec.mainClass=cc.bitbank.example.Example`

### For Scala
Add the following dependency to your project's build.sbt
```
resolvers += "bitbankinc" at "https://raw.githubusercontent.com/bitbankinc/java-bitbankcc/mvn-repo/"
　
libraryDependencies ++= Seq(
    "cc.bitbank" % "java-bitbankcc" % "2.2.0"
)

```

# How to use
## Public API
```java
import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.*;
import cc.bitbank.entity.enums.*;

Bitbankcc bb = new Bitbankcc();


Ticker ticker = bb.getTicker(CurrencyPair.BTC_JPY);
Depth depth = bb.getDepth(CurrencyPair.BTC_JPY);
Transaction[] ts = bb.getTransaction(CurrencyPair.BTC_JPY).transactions;
Transaction[] ts2 = bb.getTransaction(CurrencyPair.BTC_JPY, "20170410").transactions;
List<Ohlcv> ohlcvs = bb.getCandlestick(CurrencyPair.BTC_JPY, CandleType._1DAY, "2017").candlestick[0].getOhlcvList();
CircuitBreakInfo cbi = bb.getCircuitBreakInfo(CurrencyPair.BTC_JPY);

```

## Private API
Private api requires API_KEY and API_SECRET.
[https://bitbank.cc/account/api](https://bitbank.cc/account/api)

```java
Bitbankcc bb = new Bitbankcc();
bb.setKey("YOUR_API_KEY", "YOUR_API_SECRET");
// use requestTime method(default)
bb.setAuthMethod("requestTime");
bb.setTimeWindow(5000); // optional
// use nonce method
bb.setAuthMethod("nonce");
```

#### Assets
```java
Assets as = bb.getAsset();
```

#### Order/Orders Info
```java
Order order = bb.getOrder(CurrencyPair.BTC_JPY, 90956209);

long[] ids = {90956209, 90951996};
Orders orders = bb.getOrders(CurrencyPair.BTC_JPY, ids);
System.out.println(orders.orders[0]);
System.out.println(orders.orders[1]);
```

#### Send New Order
```java
Order order = bb.sendOrder(CurrencyPair.BTC_JPY, 130000, BigDecimal.valueOf(0.01), OrderSide.BUY, null, OrderType.LIMIT);
```

#### Cancel Order/Orders
```java
Order order = bb.cancelOrder(CurrencyPair.BTC_JPY, 129781978);

long[] ids = {129830841, 129830734};
Orders orders = bb.cancelOrders(CurrencyPair.BTC_JPY, ids);
System.out.println(orders.orders[0]);
```

#### Active Orders
Option's parameter can be seen in [document page](https://github.com/bitbankinc/bitbank-api-docs/blob/master/rest-api.md#fetch-active-orders)
```java
Map<String, Long> option = new HashMap<String, Long>();
option.put("count", 1L);
option.put("since", 1490348550380L);
Orders orders = bb.getActiveOrders(CurrencyPair.BTC_JPY, option);
for(Order o : orders.orders) {
    System.out.println(o);
}
```

#### Margin Positions
Option's parameter can be seen in [document page](https://github.com/bitbankinc/bitbank-api-docs/blob/master/rest-api.md#fetch-margin-positions)
```java
MarginPositions positions = bb.getMarginPositions();
System.out.println(positions);
```

#### Trade History
Option's parameter can be seen in [document page](https://github.com/bitbankinc/bitbank-api-docs/blob/master/rest-api.md#fetch-trade-history)
```java
Map<String, String> option = new HashMap<String, String>();
option.put("count", "1");
option.put("since", "1490348550380");
TradeHistory history = bb.getTradeHistory("btc_jpy", option);
for(Trade d : history.trades) {
    System.out.println(d);
}
```


#### Deposit History
Option's parameter can be seen in [document page](https://github.com/bitbankinc/bitbank-api-docs/blob/master/rest-api.md#fetch-deposit-history)
```java
Map<String, String> option = new HashMap<String, String>();
option.put("count", "1");
option.put("since", "1490348550380");
DepositHistory history = bb.getDepositHistory("btc", option);
for(Deposit d : history.deposits) {
    System.out.println(d);
}
```

#### Withdrawal Account Info
```java
Accounts accounts = bb.getWithdrawalAccounts("btc");
for(Accounts.Account a : accounts.accounts) {
    System.out.println(a);
}
```

#### Request Withdraw
You should set "otpToken" or "smsToken".
```java
Withdraw w = bb.requestWithdraw("btc", "XXXXXXXX-XXXX-XXXX-XXXX-XXXXXXXXXX", BigDecimal.valueOf(0.005), "867005", "");
```

#### Withdrawal History
Option's parameter can be seen in [document page](https://github.com/bitbankinc/bitbank-api-docs/blob/master/rest-api.md#fetch-withdrawal-history)
```java
Map<String, String> option = new HashMap<String, String>();
option.put("count", "1");
option.put("since", "1490348550380");
WithdrawalHistory history = bb.getWithdrawalHistory("btc", option);
for(Withdraw w : history.withdrawals) {
    System.out.println(w);
}
```


# Error Handling
```java
Bitbankcc bb = new Bitbankcc();
try {
    bb.getTicker(CurrencyPair.BTC_JPY);
} catch (BitbankException e) {
    // bitbank API error. https://docs.bitbank.cc/error_code
    System.out.println(e.code);
} catch (Exception e) {
    // other error
}
```
