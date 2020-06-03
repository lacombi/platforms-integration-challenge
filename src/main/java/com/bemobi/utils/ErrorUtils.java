package com.bemobi.utils;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.bemobi.model.Error;

public class ErrorUtils {

	public static List<Error> build(BindingResult result) {
		return result.getAllErrors().stream().map(error -> {
			if (error instanceof FieldError)
				return Error.builder()
						.field(((FieldError) error).getField())
						.message(error.getDefaultMessage())
					.build();
			return Error.builder().message(error.getDefaultMessage()).build();
		}).collect(Collectors.toList());
	}
	
}
