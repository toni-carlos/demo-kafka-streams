package com.demo.configuration;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.errors.LogAndContinueExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Map;
import java.util.Properties;

import static java.lang.Integer.parseInt;

@Configuration
public class ConfigProperties {

    @Value("${appId}")
    private String appId;

    @Value("${bootstrapServers}")
    private String bootstrapServers;
    @Value("${schemaRegistryUrl}")
    private String schemaRegistryUrl;

    @Value("${numThreads}")
    private String numThreads;

    @Bean
    public Properties streamProperties() {

        var properties = new Properties();

        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, appId);
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, SpecificAvroSerde.class);
        properties.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, parseInt(numThreads));
        properties.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        properties.put(StreamsConfig.DEFAULT_DESERIALIZATION_EXCEPTION_HANDLER_CLASS_CONFIG,
                LogAndContinueExceptionHandler.class);
        //properties.put(StreamsConfig.producerPrefix(ProducerConfig.COMPRESSION_TYPE_CONFIG), "snappy");
        //properties.put(StreamsConfig.producerPrefix(ProducerConfig.BATCH_SIZE_CONFIG), 1);
        properties.put(StreamsConfig.consumerPrefix(ConsumerConfig.MAX_POLL_RECORDS_CONFIG), 1);
        //properties.put(StreamsConfig.producerPrefix(ProducerConfig.LINGER_MS_CONFIG), 10);

        return properties;
    }

    @Bean
    public SpecificAvroSerde configKeySerde() {

        var specificAvroSerde = new SpecificAvroSerde();

        final Map<String, String> serdeConfig = Collections
                .singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

        specificAvroSerde.configure(serdeConfig, true);

        return specificAvroSerde;
    }

    @Bean
    public SpecificAvroSerde configValueSerde() {

        var specificAvroSerde = new SpecificAvroSerde();

        final Map<String, String> serdeConfig = Collections
                .singletonMap(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

        specificAvroSerde.configure(serdeConfig, false);

        return specificAvroSerde;
    }
}
