package cc.bitbank;



import cc.bitbank.entity.*;
import cc.bitbank.entity.json.*;
import cc.bitbank.exception.BitbankException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created by tanaka on 2017/04/10.
 */
public class Bitbankcc {
    private String apiKey = "";
    private String apiSecret = "";
    private String endPointPublic;
    private String endPointPrivate;

    Bitbankcc() {
        this.endPointPublic = "public.bitbank.cc";
        this.endPointPrivate = "api.bitbank.cc";
    }

    public Bitbankcc setKey(String key, String secret) {
        this.apiKey = key;
        this.apiSecret = secret;
        return this;
    }

    private URIBuilder getPublicUriBuilder(String path) {
        URIBuilder builder = new URIBuilder();
        return builder.setScheme("https")
                .setHost(this.endPointPublic)
                .setPath(path);
    }

    private List<Header> getPublicRequestHeader() {
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("content-type","application/json; charset=utf-8"));
        return headers;
    }

    private URIBuilder getPrivatecUriBuilder(String path) {
        URIBuilder builder = new URIBuilder();
        return builder.setScheme("https")
                .setHost(this.endPointPrivate)
                .setPath(path);
    }

    private List<Header> getPrivateRequestHeader(String path, List<NameValuePair> query) throws BitbankException {
        String nonce = String.valueOf(System.currentTimeMillis());
        List<Header> headers = new ArrayList<Header>();
        headers.add(new BasicHeader("content-type","application/json; charset=utf-8"));
        headers.add(new BasicHeader("ACCESS-KEY", this.apiKey));
        headers.add(new BasicHeader("ACCESS-NONCE", nonce));
        headers.add(new BasicHeader("ACCESS-SIGNATURE", createSign(path, query, nonce)));
        return headers;
    }

    private String createSign(String path, List<NameValuePair> query, String nonce) throws BitbankException {
        try {
            String algo = "HmacSHA256";
            String queryString = URLEncodedUtils.format(query, "utf-8");
            String message = nonce + path + queryString;
            String secret = this.apiSecret;

            SecretKeySpec sk = new SecretKeySpec(secret.getBytes(), algo);
            Mac mac = Mac.getInstance(algo);
            mac.init(sk);
            byte[] macBytes = mac.doFinal(message.getBytes());

            StringBuilder sb = new StringBuilder(2 * macBytes.length);
            for(byte b: macBytes) {
                sb.append(String.format("%02x", b&0xff) );
            }
            System.out.println(sb);
            return sb.toString();
        } catch (Exception e) {
            throw new BitbankException(e.getMessage());
        }
    }

    private <T extends Response> T doHttpGet(URIBuilder builder, Class<T> clazz, List<Header> header) throws BitbankException, IOException {
        try {
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            HttpClient client = HttpClientBuilder.create().setDefaultHeaders(header).build();
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
        TickerResponse result = doHttpGet(builder, TickerResponse.class, getPublicRequestHeader());
        return result.data;
    }

    Depth getDepth(CurrencyPair pair) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/depth";
        URIBuilder builder = getPublicUriBuilder(path);
        DepthResponse result = doHttpGet(builder, DepthResponse.class, getPublicRequestHeader());
        return result.data;
    }

    Transactions getTransaction(CurrencyPair pair) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/transactions";
        URIBuilder builder = getPublicUriBuilder(path);
        TransactionsResponse result = doHttpGet(builder, TransactionsResponse.class, getPublicRequestHeader());
        return result.data;
    }

    Transactions getTransaction(CurrencyPair pair, String YYYYMMDD) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/transactions/" + YYYYMMDD;
        URIBuilder builder = getPublicUriBuilder(path);
        TransactionsResponse result = doHttpGet(builder, TransactionsResponse.class, getPublicRequestHeader());
        return result.data;
    }

    Candlestick getCandlestick(CurrencyPair pair, CandleType candleType, String YYYYMMDD) throws BitbankException, IOException {
        String path = "/" + pair.getCode() + "/candlestick/" + candleType.getCode() + "/" + YYYYMMDD;
        URIBuilder builder = getPublicUriBuilder(path);
        CandlestickResponse result = doHttpGet(builder, CandlestickResponse.class, getPublicRequestHeader());
        return result.data;
    }

    Assets getAsset() throws BitbankException, IOException {
        String path = "/v1/user/assets";
        URIBuilder builder = getPrivatecUriBuilder(path);
        AssetsResponse result = doHttpGet(builder, AssetsResponse.class,
                getPrivateRequestHeader(path, Collections.<NameValuePair>emptyList()));
        return result.data;
    }
}
