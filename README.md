# About

This is a small demonstration of micro services development using spring, kafka/zookeeper. Code provides 

'DataSource, MyDataProcessor, DataSink' 

for a Data object using Kafka broker. It also provides support for CI (Contineous Integration) using Jenkins2


## Pre-requisites

In order to test this application in a docker environment; you must have docker and docker-compose installed. 


## Monitoring

Monitoring of Kafka queues is done through Grafana therefore, Kafka needs to be started with JMX exporter as command line argument together with Prometheus. In order to setup monitoring, following steps are needed for Dockers included with application;


### KAFKA related configuration

1. Login to Kafka container and download prometheus JMX java agent (as Kafka exposes JMX metrics) with kafka and place sample configuration file jmx-kafka.yml on container

wget https://repo1.maven.org/maven2/io/prometheus/jmx/jmx_prometheus_javaagent/0.6/jmx_prometheus_javaagent-0.6.jar


2. KAFKA to be started with following input parameters

KAFKA_OPTS="$KAFKA_OPTS -javaagent:/kafka/jmx_prometheus_javaagent-0.6.jar=7071:/kafka/config/jmx-kafka.yml"

Therefore edit kafka startup script in container;

- Find the line

$KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties 

- Replace this line with below line;

KAFKA_OPTS="$KAFKA_OPTS -javaagent:/kafka/jmx_prometheus_javaagent-0.6.jar=7071:/kafka/config/jmx-kafka.yml" $KAFKA_HOME/bin/kafka-server-start.sh $KAFKA_HOME/config/server.properties 


[sample jmx-kafka.yml is added to repository]


### Prometheus related configuration

Prometheus needs to know which JMX targets to monitor. In order to suggest Prometheus, prometheus.yml file is included with repository.

Therefore, prometheus container needs changing its default /etc/prometheus/prometheus.yml with the file contents provided in repository

Note: localhost:7071 is location where Kafka JMX connector is publishing so actual IP should be put instead of localhost for production.


## Building for Production ('prod') profile with Docker 

Since Kafka/Zookeeper are required for publisher/subscriber messaging system, a docker file is provided that already contains kafka, zookeeper and jenkins images. To fully dockerize this application and all the services that it depends on, build with production profile with docker support as below;

	./mvnw package -Pprod docker:build


## Initialize Docker Containers

To initialize docker container run below command from root directory:

    docker-compose -f src/main/docker/app.yml up

To stop it and remove the container, run:

    docker-compose -f src/main/docker/app.yml down


## Troubleshooting

In some instances, you will notice failure while building because following environment variables need settings;

DOCKER_CERT_PATH=/Users/<username>/.docker/machine/certs/

DOCKER_HOST=tcp://192.168.59.103:2376

DOCKER_TLS_VERIFY=1

like;

export DOCKER_HOST=tcp://192.168.99.100:2376