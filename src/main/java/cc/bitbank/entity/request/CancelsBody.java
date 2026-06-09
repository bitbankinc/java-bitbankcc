package cc.bitbank.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by tanaka on 2017/04/12.
 */
public class CancelsBody {
    public String pair;
    @JsonProperty("order_ids")
    public long[] orderIds;

    public CancelsBody(String pair, long[] orderIds) {
        this.pair = pair;
        this.orderIds = orderIds;
    }

    public String toJson() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return "{}";
        }
    }
}
