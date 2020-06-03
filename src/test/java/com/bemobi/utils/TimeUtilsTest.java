package com.bemobi.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TimeUtilsTest {

	@Test
	public void returnZeroIfLocalDateTimeIsNull() {
		long response = TimeUtils.milli(null);
		assertEquals(0, response);
	}

}
