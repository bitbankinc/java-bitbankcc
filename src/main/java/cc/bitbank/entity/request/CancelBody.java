package cc.bitbank.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by tanaka on 2017/04/12.
 */
public class CancelBody {
    public String pair;
    @JsonProperty("order_id")
    public long orderId;

    public CancelBody(String pair, long orderId) {
        this.pair = pair;
        this.orderId = orderId;
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
