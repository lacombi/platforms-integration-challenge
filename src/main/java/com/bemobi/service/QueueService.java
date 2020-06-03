package com.bemobi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.bemobi.aws.AmazonProperty;
import com.bemobi.model.Product;
import com.bemobi.utils.JsonUtils;

@Service
public class QueueService {
	
	@Autowired
	private AmazonSQS amazonSQS;
	
	@Autowired
	private AmazonProperty property;
	
	public void sendToQueue(Product product) {
		SendMessageRequest messageRequest = new SendMessageRequest()
				.withQueueUrl(this.property.getSqs().getQueueUrl())
				.withMessageBody(JsonUtils.mapToJson(product))
				.withDelaySeconds(5);
		this.amazonSQS.sendMessage(messageRequest);
	}
	
	public List<Message> getFromQueue() {
		ReceiveMessageRequest messageRequest = new ReceiveMessageRequest()
				.withQueueUrl(this.property.getSqs().getQueueUrl())
				.withWaitTimeSeconds(5);
		return this.amazonSQS.receiveMessage(messageRequest).getMessages();
	}
	
}
