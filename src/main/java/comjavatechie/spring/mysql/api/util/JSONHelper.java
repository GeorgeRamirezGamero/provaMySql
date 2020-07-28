package comjavatechie.spring.mysql.api.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.TimeZone;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;


public class JSONHelper {

    private static ObjectMapper objectMapper;
    private static Gson gson;

    public static String toJSON(Map<String, Object> map) throws JsonProcessingException {

        final ObjectMapper om = getObjectMapper();
        return om.writeValueAsString(map);
    }

    public static String toJSONwithPrettyPrint(Object map) throws JsonProcessingException {

        try {
            final ObjectMapper om = getObjectMapper();
            return om.writer() //
                    .withDefaultPrettyPrinter() //
                    .writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JsonGenerationException("JSON error", e);
        }
    }

    public static String toJSONwithNotNullAndPrettyPrint(Object map) throws JsonProcessingException {

        try {
            final ObjectMapper om = getObjectMapper();
            //            om.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return om.writer() //
                    .withDefaultPrettyPrinter() //
                    .without(SerializationFeature.WRITE_NULL_MAP_VALUES) //
                    .writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
            throw new JsonGenerationException("JSON error", e);
        }
    }

    public static ObjectMapper getObjectMapper() {

        if (null == objectMapper) {
            objectMapper = new ObjectMapper();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            objectMapper.setDateFormat(sdf);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        return objectMapper;
    }

    public static Map<String, Object> fromJSON(final String json) throws IOException {

        return getObjectMapper().readValue(json, Map.class);
    }

    public static Map<String, String> fromJSONasStringMap(final String json) throws IOException {

        return getObjectMapper().readValue(json, new TypeReference<Map<String, String>>() {
        });
    }

    public static <T> T fromJSON(final String json, final Class<T> targetClass) throws IOException {

        return getObjectMapper().readValue(json, targetClass);
    }

    public static <T> T convertValue(Object value, Class<T> klass) {

        return getObjectMapper().convertValue(value, klass);
    }
}
