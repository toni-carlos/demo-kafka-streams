package com.demo.validator;

import com.demo.streams.KafkaStreamer;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StreamValidator {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamer.class);

    public static boolean isNotEmpty(String value) {
        return null != value && !value.trim().isEmpty();
    }

    public Boolean isValid(GenericRecord key, GenericRecord value) {

        LOGGER.info("[VALIDATING] Event: " + value);

        var valid = isNotEmpty(value.get("anonymous_id").toString()) &&
                isNotEmpty(value.get("event_timestamp").toString());

        if (!valid) {
            LOGGER.warn("[INVALID] event for anonymous_id or event_timestamp: " + value);
            return false;
        }
        LOGGER.info("[VALID] event: " + value);
        return true;

    }
}
