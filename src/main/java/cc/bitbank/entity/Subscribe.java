package cc.bitbank.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by koiibb on 2025/02/26.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Subscribe extends Data {
    @JsonProperty("pubnub_channel")
    public String pubnubChannel;
    @JsonProperty("pubnub_token")
    public String pubnubToken;
    public String toString() {
        return "[Subscribe] pubnubChannel " + pubnubChannel + ", pubnuToken " + pubnubToken;
    }
}
