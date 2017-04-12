package cc.bitbank;



import cc.bitbank.entity.*;
import cc.bitbank.entity.json.*;
import cc.bitbank.exception.BitbankException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by tanaka on 2017/04/10.
 */
public class Bitbankcc {
    private String apiKey;
    private String apisecret;
    private String endPointPublic;
    private String endPointPrivate;

    Bitbankcc() {
        this.endPointPublic = "public.bitbank.cc";
        this.endPointPrivate = "https://api.bitbank.cc/v1";
    }

    public Bitbankcc setKey(String key, String secret) {
        this.apiKey = key;
        this.apisecret = secret;
        return this;
    }

    private URIBuilder getPublicUriBuilder(String path) {
        URIBuilder builder = new URIBuilder();
        return builder.setScheme("https")
                .setHost(this.endPointPublic)
                .setPath(path);
        //.setParameter("city", "130010");
    }

    private <T extends Response> T doHttpGet(URIBuilder builder, Class<T> clazz) throws BitbankException, IOException {
        try {
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            HttpClient client = HttpClientBuilder.create().build();
            HttpResponse response = client.execute(httpGet);
            HttpEntity entity = response.getEntity();
            String json = EntityUtils.toString(entity);
            System.out.println(json);

            JsonDecorder decorder = new JsonDecorder();
            T result = decorder.decode(json, clazz);
            if (result == null) {
                ErrorCodeResponse error = decorder.decode(json, ErrorCodeResponse.class);
                throw new BitbankException(error.data.code);
            } else {
                return result;
            }
        } catch (URISyntaxException e) {
            throw new BitbankException(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getLocalizedMessage());
            throw e;
        }
    }

    Ticker getTicker(CurrencyPair pair) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/ticker";
        URIBuilder builder = getPublicUriBuilder(path);
        TickerResponse result = doHttpGet(builder, TickerResponse.class);
        return result.data;
    }

    Depth getDepth(CurrencyPair pair) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/depth";
        URIBuilder builder = getPublicUriBuilder(path);
        DepthResponse result = doHttpGet(builder, DepthResponse.class);
        return result.data;
    }

    Transactions getTransaction(CurrencyPair pair) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/transactions";
        URIBuilder builder = getPublicUriBuilder(path);
        TransactionsResponse result = doHttpGet(builder, TransactionsResponse.class);
        return result.data;
    }

    Transactions getTransaction(CurrencyPair pair, String YYYYMMDD) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/transactions/" + YYYYMMDD;
        URIBuilder builder = getPublicUriBuilder(path);
        TransactionsResponse result = doHttpGet(builder, TransactionsResponse.class);
        return result.data;
    }

    Candlestick getCandlestick(CurrencyPair pair, CandleType candleType, String YYYYMMDD) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/candlestick/" + candleType.getCode() + "/" + YYYYMMDD;
        URIBuilder builder = getPublicUriBuilder(path);
        CandlestickResponse result = doHttpGet(builder, CandlestickResponse.class);
        return result.data;
    }
}
