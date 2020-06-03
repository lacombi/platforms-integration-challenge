package com.bemobi.resource;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bemobi.dto.ProductDto;
import com.bemobi.model.Product;
import com.bemobi.service.ProductService;
import com.bemobi.service.QueueService;

@RestController
@RequestMapping("/products")
public class ProductResource {

	@Autowired
	private QueueService queueService;
	
	@Autowired
	private ProductService productService;

	private static final Logger LOG = LoggerFactory.getLogger(ProductResource.class);

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HttpStatus> product(@Valid @RequestBody ProductDto dto) {
		LOG.info("HTTP request received:" + dto);
		Product product = this.productService.create(dto.build());
		this.queueService.sendToQueue(product);
		LOG.info("Sent to queue:" + product);
		return ResponseEntity.accepted().build();
	}

}
