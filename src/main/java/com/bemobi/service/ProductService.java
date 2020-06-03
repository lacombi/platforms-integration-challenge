package com.bemobi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.bemobi.enums.ProductStatus;
import com.bemobi.model.Product;
import com.bemobi.utils.TimeUtils;

@Service
public class ProductService {

	public Product create(Product product) {
		LocalDateTime dateTime = LocalDateTime.now();
		product.setStatus(ProductStatus.ACTIVE);
		product.setCreatedAt(dateTime);
		product.setTtl(TimeUtils.milliToString(dateTime, 10));
		return product;
	}

}