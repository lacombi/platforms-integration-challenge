from locust import HttpUser, TaskSet, task, between
import json

class MyUser(HttpUser):
    wait_time = between(5, 15)

    @task(1)
    def product(self):
        data = {
            "productId": "8bac677a-1078-4a4d-b8ba-2877b52944ad",
            "amount": "100"
        }
        self.client.post("/products", data=json.dumps(data), headers={
            'Content-Type': 'application/json'
        })