# java-bitbankcc

Java library for bitbank.cc, Bitcoin exchange.

[https://bitbank.cc/](https://bitbank.cc/)

API document: [https://docs.bitbank.cc/](https://docs.bitbank.cc/)

# Breaking Changes in v3.0.0

## Deprecated CurrencyPair enum

The `CurrencyPair` enum has been deprecated and removed in v3.0.0. Please use string-based currency pair codes instead.

### Migration Guide

**Before (v2.x):**

```java
import cc.bitbank.entity.enums.CurrencyPair;

// Using CurrencyPair enum
Order order = bb.sendOrder(CurrencyPair.BTC_JPY, 130000, BigDecimal.valueOf(0.01), OrderSide.BUY, null, OrderType.LIMIT);
Order orderInfo = bb.getOrder(CurrencyPair.BTC_JPY, 90956209);
TradeHistory history = bb.getTradeHistory(CurrencyPair.BTC_JPY, option);
```

**After (v3.0.0):**

```java
// Using string-based currency pair codes
Order order = bb.sendOrder("btc_jpy", 130000, BigDecimal.valueOf(0.01), OrderSide.BUY, OrderType.LIMIT);
Order orderInfo = bb.getOrder("btc_jpy", 90956209);
TradeHistory history = bb.getTradeHistory("btc_jpy", option);
```

Available currency pairs can be found at: [https://github.com/bitbankinc/bitbank-api-docs/blob/master/pairs.md](https://github.com/bitbankinc/bitbank-api-docs/blob/master/pairs.md)

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
        <version>3.0.0</version>
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
    "cc.bitbank" % "java-bitbankcc" % "3.0.0"
)

```

# How to use

## Public API

```java
import cc.bitbank.Bitbankcc;
import cc.bitbank.entity.*;
import cc.bitbank.entity.enums.*;

Bitbankcc bb = new Bitbankcc();


Ticker ticker = bb.getTicker("btc_jpy");
Depth depth = bb.getDepth("btc_jpy");
Transaction[] ts = bb.getTransaction("btc_jpy").transactions;
Transaction[] ts2 = bb.getTransaction("btc_jpy", "20170410").transactions;
List<Ohlcv> ohlcvs = bb.getCandlestick("btc_jpy", CandleType._1DAY, "2017").candlestick[0].getOhlcvList();
CircuitBreakInfo cbi = bb.getCircuitBreakInfo("btc_jpy");

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
Order order = bb.getOrder("btc_jpy", 90956209);

long[] ids = {90956209, 90951996};
Orders orders = bb.getOrders("btc_jpy", ids);
System.out.println(orders.orders[0]);
System.out.println(orders.orders[1]);
```

#### Send New Order

```java
Order order = bb.sendOrder("btc_jpy", 130000, BigDecimal.valueOf(0.01), OrderSide.BUY, OrderType.LIMIT);
```

#### Cancel Order/Orders

```java
Order order = bb.cancelOrder("btc_jpy", 129781978);

long[] ids = {129830841, 129830734};
Orders orders = bb.cancelOrders("btc_jpy", ids);
System.out.println(orders.orders[0]);
```

#### Active Orders

Option's parameter can be seen in [document page](https://github.com/bitbankinc/bitbank-api-docs/blob/master/rest-api.md#fetch-active-orders)

```java
Map<String, Long> option = new HashMap<String, Long>();
option.put("count", 1L);
option.put("since", 1490348550380L);
Orders orders = bb.getActiveOrders("btc_jpy", option);
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

#### Get subscribe channel & token

```java
Subscribe sub = bb.getSubscribe();
```

# Error Handling

```java
Bitbankcc bb = new Bitbankcc();
try {
    bb.getTicker("btc_jpy");
} catch (BitbankException e) {
    // bitbank API error. https://docs.bitbank.cc/error_code
    System.out.println(e.code);
} catch (Exception e) {
    // other error
}
```
