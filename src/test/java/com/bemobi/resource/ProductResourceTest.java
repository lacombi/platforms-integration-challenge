package com.bemobi.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.amazonaws.services.sqs.model.Message;
import com.bemobi.BemobiApplication;
import com.bemobi.model.Product;
import com.bemobi.service.QueueService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;


@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BemobiApplication.class)
public class ProductResourceTest {

	@Autowired
    private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	private QueueService queueService;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void successRequest() throws Exception {
		String productId = "8bac677a-1078-4a4d-b8ba-2877b52944ad";
		
		String inputRequest = "{\"productId\":\"" + productId + "\",\"amount\":\"100\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products")
				.accept(MediaType.APPLICATION_JSON)
				.content(inputRequest)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.ACCEPTED.value(), response.getStatus());
		
		this.assertQueuedMessage(productId, 1);
	}
	
	@Test
	public void badRequestNoContent() throws Exception {
		String inputRequest = "{}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products")
				.accept(MediaType.APPLICATION_JSON)
				.content(inputRequest)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	@Test
	public void badRequestNoAmount() throws Exception {
		String inputRequest = "{\"productId\":\"8bac677a-1078-4a4d-b8ba-2877b52944ad\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products")
				.accept(MediaType.APPLICATION_JSON)
				.content(inputRequest)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	@Test
	public void badRequestNoProductId() throws Exception {
		String inputRequest = "{\"amount\":\"100\"}";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/products")
				.accept(MediaType.APPLICATION_JSON)
				.content(inputRequest)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
	}
	
	@SneakyThrows
	private void assertQueuedMessage(String productId, int queuedMessages) {
		List<Message> messages = this.queueService.getFromQueue();
		assertEquals(queuedMessages, messages.size());
		
		if (queuedMessages > 0) {
			Message msg = messages.get(0);
			Product product = new ObjectMapper().readValue(msg.getBody(), Product.class);
			assertEquals(productId, product.getProductId());
		}
	}

}
