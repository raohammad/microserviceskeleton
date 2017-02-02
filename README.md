# About

This is a small demonstration of micro services development using spring, kafka/zookeeper. Code provides 

'DataSource, MyDataProcessor, DataSink' 

for a Data object using Kafka broker. It also provides support for CI (Contineous Integration) using Jenkins2

## Pre-requisites

In order to test this application in a docker environment; you must have docker and docker-compose installed. 

## Support for 'dev' and 'prod' profiles

This repository supports two modes of deployment. If you open this repository with Eclipse and running as a Spring Boot application; by default 'dev' profile is selected. In this case, application-dev.yml will be effective for spring configurations.


## Building for Production ('prod') profile with Docker 

Since Kafka/Zookeeper are required for publisher/subscriber messaging system, a docker file is provided that already contains kafka, zookeeper and jenkins images. To fully dockerize this application and all the services that it depends on, build with production profile with docker support as below;

	./mvnw package -Pprod docker:build


## Initialize Docker Containers

To initialize docker container run below command from root directory:

    docker-compose -f src/main/docker/app.yml up

To stop it and remove the container, run:

    docker-compose -f src/main/docker/app.yml down

Note: This skeleton is ready for extension for AngularJS based front-end as well. 
