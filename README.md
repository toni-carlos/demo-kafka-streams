# Kafka Streams Session

Essa aplicação é um exemplo de como podemos usar o Kafka Streams para criar aplicação statefull usando KTable para recuperar
o valor mais recente da chave enviada para o tópico de changelog. Essa aplicação cria um atributo 
sessionID para cada evento de interação do usuário na loja fake-compreja durante o período de janela de 30 minutos de um evento para o outro.
Ou seja, caso o evento atual tenha um intervalo de tempo maior que 30 minutos do anterior, então é criado para o usuário um novo SessionID, 
caracterizando tempo de inatividade na loja fake-compreja.

## Application Diagram
Here is a simple diagram for this application:
![Application Diagram](doc/diagrama-aplicacao.png)

A aplicação ler eventos do tópico de entrada `user-events`, cria o sessionID para o usuário e envia cada evento para 
o tópico de saída `user-sessions`.

## Requirements
* Docker + Docker-Compose: We will use it to start broker, schema registry and zookeeper.
* Java 11
* An IDE like Intellij IDEA
* 
## Running the project

### 1. Starting Kafka

We need to start the services Broker, schema registry and zookeeper. For that we have a 
[docker-compose.yml file](docker-compose.yml) that will create the necessary resources for us. To make things easier, 
some utility commands were created to help create these services:

```shell
make start_containers
```

### 2. Create the input/output topics.

```shell
make start_containers
```
### 3. Create Avro Schema in Schema Registry

```shell
chmod +x scripts
make create_schemas
```

### 4. Write some input data to the source topics

write input data using the example found in the producer-user-events-for-sessionizer branch. Follow the steps in the readme to run the project.


### 5. Validate that data was sent to the source topic.

```shell
chmod +x scripts
make consumer_topic TOPIC=user-events
```

### 6. Now let's run the Sessionizer application
```shell
gradle build run
```

## Running the Tests
```shell
gradle test
```

## Understanding the Topology

![Topology](doc/topology_sessionizer.png)

1️⃣ **Filter** operation to map 1 record into many. In our case, every sentences is mapped into multiple records: one for each word in the sentence. Also the case is lowered to make the process case-insensitive.

2️⃣ **Map** stream selecting a grouping key. In our case, the word. This will always return grouped stream, prepared to be aggregated. It will also trigger an operation called **repartition**. We will learn more about this later.

3️⃣ **Left Join** every appearance of the key in the stream. This will be stored in a **data store**.

4️⃣ **To** Finally, we stream the results into the topic `user-sessions`. We can stream a table using the method `toStream`, which will stream the latest value that was stored for a given key, everytime that key is updated.














