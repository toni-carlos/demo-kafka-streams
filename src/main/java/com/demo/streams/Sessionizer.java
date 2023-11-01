package com.demo.streams;

import com.demo.avro.UserSessionsValue;
import org.apache.avro.generic.GenericRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class Sessionizer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamer.class);
    private static final long SESSION_WINDOW = TimeUnit.MINUTES.toMillis(30);

    private static String getSessionId(UserSessionsValue event, UserSessionsValue previousEvent) {
        LOGGER.info("[GETTING SESSION ID] for event: " + event);

        Long previousEventTimestamp;
        Long eventTimestamp;

        String sessionId;

        if (previousEvent == null) {
            LOGGER.info("[PREVIOUS] does not exist.");
            sessionId = UUID.randomUUID().toString();
        } else {
            LOGGER.info("[PREVIOUS] exist.");

            // Temporary conversion of currently on the fly events with timestamp in seconds
            previousEventTimestamp = Long.parseLong(previousEvent.getEventTimestamp().toString());
            eventTimestamp = Long.parseLong(event.getEventTimestamp().toString());

            if (previousEventTimestamp < 10000000000L) {
                previousEventTimestamp *= 1000;
            }

            if (eventTimestamp < 10000000000L) {
                eventTimestamp *= 1000;
            }

            if ((previousEventTimestamp + SESSION_WINDOW) < eventTimestamp) {
                LOGGER.info("[EVENT OUTSIDE] the window.");
                sessionId = UUID.randomUUID().toString();
            } else {
                LOGGER.info("[EVENT INSIDE] the window.");
                sessionId = previousEvent.getSessionId().toString();
            }
        }

        return sessionId;
    }

    public static GenericRecord transform(UserSessionsValue event, UserSessionsValue previousEvent) {

        event.setSessionId(getSessionId(event, previousEvent));

        return event;
    }
}