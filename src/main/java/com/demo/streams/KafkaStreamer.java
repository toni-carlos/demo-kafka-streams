package com.demo.streams;

import com.demo.avro.UserEventsKey;
import com.demo.avro.UserEventsValue;
import com.demo.avro.UserSessionsValue;
import com.demo.validator.StreamValidator;
import io.confluent.kafka.schemaregistry.client.MockSchemaRegistryClient;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Properties;

@Service
public class KafkaStreamer {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaStreamer.class);
    private final StreamValidator streamValidator;
    private final SpecificAvroSerde configValueSerde;
    private final SpecificAvroSerde configKeySerde;
    private final Properties streamProperties;
    private final EnrichedEvent enrichedEvent;
    @Value("${outputTopic}")
    private String outputTopic;
    @Value("${inputTopic}")
    private String inputTopic;
    @Value("${bootstrapServers}")
    private String bootstrapServers;
    @Value("${schemaRegistryUrl}")
    private String schemaRegistryUrl;
    @Value("${numThreads}")
    private String numThreads;


    public KafkaStreamer(SpecificAvroSerde configValueSerde, SpecificAvroSerde configKeySerde,
                         StreamValidator streamValidator, Properties streamProperties, EnrichedEvent enrichedEvent) {
        this.configValueSerde = configValueSerde;
        this.configKeySerde = configKeySerde;
        this.streamValidator = streamValidator;
        this.streamProperties = streamProperties;
        this.enrichedEvent = enrichedEvent;
    }


    public void start() {

        LOGGER.info("[STARTING TOPOLOGY PROCESSING]");

        final KafkaStreams streams = new KafkaStreams(buildTopology(), streamProperties);

        streams.setUncaughtExceptionHandler((Thread thread, Throwable throwable) -> {
            LOGGER.error("An unexpected exception is encountered", throwable);
        });

        streams.cleanUp();
        streams.start();
    }

    public Topology buildTopology() {
        // create and configure the SpecificAvroSerdes required in this example
        StreamsBuilder builder = new StreamsBuilder();

        KTable sessionizedEvents = builder.table(outputTopic, Consumed.with(configKeySerde, configValueSerde));

        builder.stream(
                this.inputTopic, Consumed.with(configKeySerde, configValueSerde)
        ).filter(    // Filter empty messages due to json encoding error
                (key, value) -> streamValidator.isValid((UserEventsKey) key, (UserEventsValue) value)
        ).map(
                (key, value) -> enrichedEvent.enrich((UserEventsKey) key, (UserEventsValue) value)
        ).leftJoin(  // Sessioning
                sessionizedEvents,
                (event, previousEvent) -> Sessionizer.transform((UserSessionsValue) event, (UserSessionsValue) previousEvent)
        ).to(
                this.outputTopic, Produced.with(configKeySerde, configValueSerde)
        );

        return builder.build();
    }


}
