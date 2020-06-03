package com.bemobi.aws;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Component
@ConfigurationProperties("amazon")
public class AmazonProperty {

	private final SQS sqs = new SQS();

	@Getter
	@Setter
	public static class SQS {
		private String region;
		private String sqsUrl;
		private String queueUrl;
		private String accessKeyId;
		private String secretKeyId;
	}

}
