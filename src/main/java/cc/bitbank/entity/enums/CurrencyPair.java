package cc.bitbank.entity.enums;

/**
 * Created by tanaka on 2017/04/11.
 */

public enum CurrencyPair {
    BTC_JPY("btc_jpy"),
    LTC_JPY("ltc_jpy"),
    LTC_BTC("ltc_btc"),
    XRP_JPY("xrp_jpy"),
    XRP_BTC("xrp_btc"),
    ETH_JPY("eth_jpy"),
    ETH_BTC("eth_btc"),
    MONA_JPY("mona_jpy"),
    MONA_BTC("mona_btc"),
    BCC_JPY("bcc_jpy"),
    BCC_BTC("bcc_btc"),
    XLM_JPY("xlm_jpy"),
    XLM_BTC("xlm_btc"),
    QTUM_JPY("qtum_jpy"),
    QTUM_BTC("qtum_btc"),
    BAT_JPY("bat_jpy"),
    BAT_BTC("bat_btc"),
    OMG_JPY("omg_jpy"),
    OMG_BTC("omg_btc");

    private final String pair;

    CurrencyPair(final String pair) {
        this.pair = pair;
    }

    public String getCode() {
        return this.pair;
    }
}
