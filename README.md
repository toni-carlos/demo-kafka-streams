# Kafka Streams Demo

This repository contains branches with code examples that demonstrate how to implement real-time applications and event-driven
microservices using the Streams API of [Apache Kafka](http://kafka.apache.org/) aka Kafka Streams.

For more information take a look at the
[**latest Confluent documentation on the Kafka Streams API**](http://docs.confluent.io/current/streams/), notably the
[**Developer Guide**](https://docs.confluent.io/platform/current/streams/developer-guide/index.html)

<a name="examples-apps"/>

## Examples: Runnable Applications

| Application Name                                                                                        | Description                                                                                                          |
|---------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------|
| [Session Creator](https://github.com/toni-carlos/demo-kafka-streams/tree/kafka-streams-session-creator) | creates session_id for user interaction events based on the 30 minute window of downtime from one event to the next. |
| Anomaly Identifier (Not started)                                                                        | Identifies if there were more than 20 login attempts within a 5-second window                                        |
| Order Enricher (Not starte)                                                                             | Enriches the purchase order event with other information found in other topics                                       |
