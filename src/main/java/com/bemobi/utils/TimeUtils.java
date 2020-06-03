package com.bemobi.utils;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.util.ObjectUtils;

public class TimeUtils {

	public static long milli(LocalDateTime localDateTime) {
		if (!ObjectUtils.isEmpty(localDateTime)) {
			return localDateTime.toInstant(ZoneOffset.UTC).toEpochMilli();
		}
		return 0;
	}

	public static long milli(LocalDateTime localDateTime, int numberOfDays) {
		if (!ObjectUtils.isEmpty(localDateTime)) {
			localDateTime.plusDays(numberOfDays);
			return milli(localDateTime);
		}
		return 0;
	}

	public static String milliToString(LocalDateTime localDateTime) {
		return String.valueOf(milli(localDateTime));
	}

	public static String milliToString(LocalDateTime localDateTime, int numberOfDays) {
		return String.valueOf(milli(localDateTime, numberOfDays));
	}

}
