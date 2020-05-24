package cc.bitbank.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import cc.bitbank.entity.response.Response;
import cc.bitbank.exception.BitbankException;

/**
 * Created by tanaka on 2017/04/11.
 */
public class JsonDecorder {

	public <T extends Response<?>> T decode(String json, Class<T> clazz) throws BitbankException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            T result = mapper.readValue(json, clazz);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
