package com.bemobi.aws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;

@Configuration
public class AmazonConfig {

	@Autowired
	private AmazonProperty property;

	@Bean
	public AmazonSQS amazonSQS() {
		BasicAWSCredentials awsCredentials = new BasicAWSCredentials(this.property.getSqs().getAccessKeyId(), this.property.getSqs().getSecretKeyId());
		EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(this.property.getSqs().getSqsUrl(), this.property.getSqs().getRegion());
		return AmazonSQSClientBuilder.standard()
				.withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
				.withEndpointConfiguration(endpoint).build();
	}

}
