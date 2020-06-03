package com.bemobi.dto;

import javax.validation.constraints.NotBlank;

import com.bemobi.model.Product;

import lombok.Data;

@Data
public class ProductDto {

	@NotBlank
	private String productId;

	@NotBlank
	private String amount;

	public Product build() {
		return Product.builder()
				.productId(productId)
				.amount(Double.valueOf(this.amount) / 10000d)
			.build();
	}

}
