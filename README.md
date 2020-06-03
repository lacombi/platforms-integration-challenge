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
    - com.bemobichallange.resource
    - com.bemobichallange.utils



## CONFIGURATION
---
To build this project locally, open the main directory and insert the following commands:

```
docker-compose build
docker-compose up
```



## REQUIREMENT
---
- JDK 8
- Any IDE that runs Spring Boot
    - e.g STS - [https://spring.io/tools](SpringToolsSuit)
- Docker - [https://www.docker.com](Docker)
- MakeFile (Optional for fast building) - [https://www.gnu.org/software/make](MakeFile)



## BASICS
---
Usage typically looks like this:

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