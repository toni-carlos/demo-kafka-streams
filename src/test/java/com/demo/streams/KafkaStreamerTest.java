package com.demo.streams;

import com.demo.avro.UserEventsKey;
import com.demo.avro.UserEventsValue;
import com.demo.avro.UserSessionsKey;
import com.demo.avro.UserSessionsValue;
import io.confluent.kafka.schemaregistry.testutil.MockSchemaRegistry;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.streams.TestInputTopic;
import org.apache.kafka.streams.TestOutputTopic;
import org.apache.kafka.streams.TopologyTestDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
class KafkaStreamerTest {

    // A mocked schema registry for our serdes to use
    private static final String SCHEMA_REGISTRY_SCOPE = KafkaStreamerTest.class.getName();
    private static final String MOCK_SCHEMA_REGISTRY_URL = "mock://" + SCHEMA_REGISTRY_SCOPE;
    private final Map<String, String> AVRO_SERDE_CONFIG = Collections.singletonMap(
            AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, MOCK_SCHEMA_REGISTRY_URL
    );
    @Autowired
    KafkaStreamer kafkaStreamer;
    @Autowired
    Properties streamProperties;
    private TopologyTestDriver topologyTestDriver;
    private TestInputTopic<UserEventsKey, UserEventsValue> input;
    private TestOutputTopic<UserSessionsKey, UserSessionsValue> output;

    @BeforeEach
    public void init() {

        topologyTestDriver = new TopologyTestDriver(
                kafkaStreamer.buildTopology(), streamProperties);

        final SpecificAvroSerde<UserEventsKey> eventSerializerKey = new SpecificAvroSerde();
        eventSerializerKey.configure(AVRO_SERDE_CONFIG, true);

        final SpecificAvroSerde<UserEventsValue> eventSerializerValue = new SpecificAvroSerde();
        eventSerializerValue.configure(AVRO_SERDE_CONFIG, false);

        final SpecificAvroSerde<UserSessionsKey> sessionDeserializerKey = new SpecificAvroSerde();
        sessionDeserializerKey.configure(AVRO_SERDE_CONFIG, true);

        final SpecificAvroSerde<UserSessionsValue> sessionDeserializerValue = new SpecificAvroSerde();
        sessionDeserializerValue.configure(AVRO_SERDE_CONFIG, false);

        input = topologyTestDriver.createInputTopic("user-events", eventSerializerKey.serializer(), eventSerializerValue.serializer());

        output = topologyTestDriver.createOutputTopic("user-sessions", sessionDeserializerKey.deserializer(), sessionDeserializerValue.deserializer());
    }

    @AfterEach
    public void closeStreams() {
        if (topologyTestDriver != null) {
            topologyTestDriver.close();
        }
        MockSchemaRegistry.dropScope(SCHEMA_REGISTRY_SCOPE);
    }

    @Test
    public void eventsInsideTheWindow_shouldCreateTheSameSessionIdForTheAnonymousID() {

        var userEventsKey = new UserEventsKey();
        userEventsKey.setAnonymousId(1);

        var user1Event1Value = new UserEventsValue();
        user1Event1Value.setAnonymousId(1);
        user1Event1Value.setAction("login");
        user1Event1Value.setEventTimestamp("1697504400");
        user1Event1Value.setEventDatetime("2023-10-17 01:00:00");

        var user1Event2Value = new UserEventsValue();
        user1Event2Value.setAnonymousId(1);
        user1Event2Value.setAction("click_product");
        user1Event2Value.setEventTimestamp("1697504520");
        user1Event2Value.setEventDatetime("2023-10-17 01:02:00");

        var user1Event3Value = new UserEventsValue();
        user1Event3Value.setAnonymousId(1);
        user1Event3Value.setAction("product_detail");
        user1Event3Value.setEventTimestamp("1697504522");
        user1Event3Value.setEventDatetime("2023-10-17 01:02:02");


        input.pipeInput(userEventsKey, user1Event1Value);
        input.pipeInput(userEventsKey, user1Event2Value);
        input.pipeInput(userEventsKey, user1Event3Value);

        final var sessions = output.readKeyValuesToList();

        assertEquals(sessions.get(0).value.getSessionId(), sessions.get(1).value.getSessionId());
        assertEquals(sessions.get(1).value.getSessionId(), sessions.get(2).value.getSessionId());
    }

    @Test
    public void eventsOutsideTheWindow_shouldCreateTheDifferentSessionIdForTheAnonymousID() {

        var userEventsKey = new UserEventsKey();
        userEventsKey.setAnonymousId(2);

        var user2Event1Value = new UserEventsValue();
        user2Event1Value.setAnonymousId(2);
        user2Event1Value.setAction("login_error");
        user2Event1Value.setEventTimestamp("1697504400");
        user2Event1Value.setEventDatetime("2023-10-17 01:00:00");

        var user2Event2Value = new UserEventsValue();
        user2Event2Value.setAnonymousId(2);
        user2Event2Value.setAction("login");
        user2Event2Value.setEventTimestamp("1697508001");
        user2Event2Value.setEventDatetime("2023-10-17 02:00:01");


        input.pipeInput(userEventsKey, user2Event1Value);
        input.pipeInput(userEventsKey, user2Event2Value);

        final var sessions = output.readKeyValuesToList();

        assert sessions.get(0).value.getSessionId() != sessions.get(1).value.getSessionId();
    }

}