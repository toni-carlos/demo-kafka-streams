package com.demo.streams;

import com.demo.avro.UserEventsKey;
import com.demo.avro.UserEventsValue;
import com.demo.avro.UserSessionsKey;
import com.demo.avro.UserSessionsValue;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.streams.KeyValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class EnrichedEvent {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamer.class);

    public KeyValue<GenericRecord, GenericRecord> enrich(UserEventsKey key, UserEventsValue event) {

        LOGGER.info("[ENRICHING] the event: " + event);

        var userSessionsValue = new UserSessionsValue();
        var userSessionsKey = new UserSessionsKey();
        userSessionsValue.setAnonymousId((Integer) event.get("anonymous_id"));
        userSessionsValue.setAction((CharSequence) event.get("action"));
        userSessionsValue.setEventTimestamp((CharSequence) event.get("event_timestamp"));
        userSessionsValue.setEventDatetime((CharSequence) event.get("event_datetime"));

        userSessionsKey.setAnonymousId((Integer) event.get("anonymous_id"));


        return new KeyValue<>(userSessionsKey, userSessionsValue);
    }
}
