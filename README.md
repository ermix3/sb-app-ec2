# sb-app-ec2

## Description

> This repository contains a Spring Boot application that is deployed on an EC2 instance using Docker. The application is
a simple REST API that handle CRUD products.
The application use MySQL, with flyway.

## For github actions need to set up the following secrets

- ***DOCKERHUB_USERNAME*** => Your Docker Hub username
- ***DOCKERHUB_TOKEN*** => A Docker Hub access token (recommended over password)


- ***EC2_SSH_PRIVATE_KEY*** => Your private SSH key (the public one should be on EC2)
- ***EC2_PUBLIC_IP*** => Public IP address of your EC2 instance


- ***MYSQL_ROOT_PASSWORD*** => ROOT password
- ***SPRING_DATASOURCE_USERNAME*** => DB user
- ***SPRING_DATASOURCE_PASSWORD*** => USER password
