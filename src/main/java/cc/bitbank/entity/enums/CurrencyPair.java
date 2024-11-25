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
    OMG_BTC("omg_btc"),
    XYM_JPY("xym_jpy"),
    XYM_BTC("xym_btc"),
    LINK_JPY("link_jpy"),
    LINK_BTC("link_btc"),
    MKR_JPY("mkr_jpy"),
    MKR_BTC("mkr_btc"),
    BOBA_JPY("boba_jpy"),
    BOBA_BTC("boba_btc"),
    ENJ_JPY("enj_jpy"),
    ENJ_BTC("enj_btc"),
    MATIC_JPY("matic_jpy"),
    MATIC_BTC("matic_btc"),
    DOT_JPY("dot_jpy"),
    DOGE_JPY("doge_jpy"),
    ASTR_JPY("astr_jpy"),
    ADA_JPY("ada_jpy"),
    AVAX_JPY("avax_jpy"),
    AXS_JPY("axs_jpy"),
    FLR_JPY("flr_jpy"),
    SAND_JPY("sand_jpy"),
    GALA_JPY("gala_jpy"),
    APE_JPY("ape_jpy"),
    CHZ_JPY("chz_jpy"),
    OAS_JPY("oas_jpy"),
    MANA_JPY("mana_jpy"),
    GRT_JPY("grt_jpy"),
    RNDR_JPY("rndr_jpy"),
    BNB_JPY("bnb_jpy"),
    DAI_JPY("dai_jpy"),
    OP_JPY("op_jpy"),
    ARB_JPY("arb_jpy"),
    KLAY_JPY("klay_jpy"),
    IMX_JPY("imx_jpy"),
    MASK_JPY("mask_jpy"),
    POL_JPY("pol_jpy"),
    SOL_JPY("sol_jpy"),
    CYBER_JPY("cyber_jpy");

    private final String pair;

    CurrencyPair(final String pair) {
        this.pair = pair;
    }

    public String getCode() {
        return this.pair;
    }
}
