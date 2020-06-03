package com.bemobi.enums;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ProductStatus {

	@JsonProperty("active")
	ACTIVE,

	@JsonProperty("inactive")
	INACTIVE;
	
}
