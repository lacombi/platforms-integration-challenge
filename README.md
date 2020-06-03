<<<<<<< HEAD
# DEVELOP BEMOBI CHALLANGE
---
![Screenshot](imgs/bemobi-logo.PNG)


## STORY
---
We received an integration demand. In this integration, we received an HTTP request and we want to send the payload to an SQS queue. But, we added some new fields to this payload before sending it to the queue.

This is the contract that our client sent to us:
```json
{
    "productId": "8bac677a-1078-4a4d-b8ba-2877b52944ad",
    "amount": "100"
}
```
This is the model contract we saved in the queue:
```json
{
    "productId": "8bac677a-1078-4a4d-b8ba-2877b52944ad",
    "amount": 0.01,
    "status": "active",
    "createdAt": "2020-04-30T22:33:16.497Z",
    "ttl": "1588285962993"
}
```



## SPECIFICATIONS,
---
Our **REST API** was configured to run on **PORT 8085**

For SQS Queue we used [https://hub.docker.com/r/roribio16/alpine-sqs](alpine-sqs):

- For **SQS Insight**, we are using **PORT 9325**
- For **Sending, Receiving, and Deleting Queues**, we are using **PORT 9394**

For **load tests**, we used [https://locust.io](locust):

- To run the load tests, just navigate to http://localhost:8089 to open the locust page on your browser and then, fill the fields for test simulation
- Attention!! If you are running from **Windows**, set up the HOST to http://host.docker.internal:8085, like above:

    ![Screenshot](imgs/locust.PNG)

For **integration test**, we used JUnit. To see the tests, just go to:

- Folder: src/test/java
- Packages:
    - com.bemobi.resource
    - com.bemobi.utils



## CONFIGURATION
---
Before sent the Product HTTP Request to our service, we must set up the SQS Queue service and Locust service. Because our REST API will try to make a connection with them.

So, we just need to open a command-line in the ROOT project directory and run the following command:

```
docker-compose up
```



## REQUIREMENT
---
- JDK 8
- Any IDE that runs a Spring Boot Java project
    - e.g STS - [https://spring.io/tools](SpringToolsSuit)
- Docker - [https://www.docker.com](Docker)
- MakeFile (Optional for fast building) - [https://www.gnu.org/software/make](MakeFile)



## BASICS
---

Basically summarizing, first of all, open a command-line in the ROOT project directory and run the command:

```
docker-compose up
```

Then, open the java project and run it. The usage typically looks like this:

POST: http://localhost:8085/products
```json
{
    "productId": "8bac677a-1078-4a4d-b8ba-2877b52944ad",
    "amount": "100"
}
```

Result
```
HTTP Status 202 - Accepted
```

Sent to the Product Queue
```json
{
    "productId": "8bac677a-1078-4a4d-b8ba-2877b52944ad",
    "amount": 0.01,
    "status": "active",
    "createdAt": "2020-04-30T22:33:16.497Z",
    "ttl": "1588285962993"
}
```

## BASICS WITHOUT JAVA OR STS IDE INSTALLED

Oh, no!! I don't have Java neither i can't execute the STS Java project in my machine.. So, what now??

Just relax !! There is no problem, you can run our project in a docker container, just run the following command in your command-line from the ROOT project directory.

```
docker-compose -f docker-compose.yml -f docker-compose.java.yml up
```

After that just repeat the **BASICS Section**, but you don't need to run ```docker-compose up``` command anymore, just skip that.
=======
# platforms-integration-challenge
Welcome to the challenge of implementing platform integration.

In this repository, you will be guided on how to develop and deliver a software project to the Bemobi Platforms team.

Before you start, pay close attention to these instructions.

- Fork the project.
- Create pull requestes for each implemented feature.
- To deliver the project, create a branch called "develop" and merge the other branches created in it.
- Document what you think is necessary from readme.md.

## Story
We received an integration demand.
In this integration, we need to receive an HTTP request in a rest api and send the payload to an SQS queue. But, we will need to add some new fields to this payload before sending it to the queue.

This is the contract that our client will send:
```json
{
  "productId": "8bac677a-1078-4a4d-b8ba-2877b52944ad",
  "amount": "100"
}
```
This is the model contract we want to save in the queue:
```json
{
  "productId": "8bac677a-1078-4a4d-b8ba-2877b52944ad",
  "amount": 0.01,
  "status": "active",
  "createdAt": "2020-04-30T22:33:16.497Z",
  "ttl": "1588285962993"
}
```

His team decided that it is necessary to create integration tests in this project and, if possible, load testing.

## Requirement
The project must be done with the language and libraries of your choice.

## Help
To integration tests with SQS you can use this docker container: [roribio16/alpine-sqs](https://hub.docker.com/r/roribio16/alpine-sqs)

For load tests, we suggest using the [locust](https://locust.io), but this is your choice.

Good luck!
>>>>>>> b5052f7e8c22680f354aba67c42b2fc29a1a485d
