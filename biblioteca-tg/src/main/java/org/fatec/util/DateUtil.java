package org.fatec.util;

import java.util.Calendar;
import java.util.Map;

public class DateUtil {
	public static final int MIN_YEAR = 2000;
	public static final int MAX_YEAR_PERMITTED = 2100;
	public static final int MAX_YEAR = Calendar.getInstance().get(Calendar.YEAR);

	private DateUtil() {
	}

	public static void populaMapaAnos(Map<String, Integer> ano) {
		for (int i = MIN_YEAR; i <= MAX_YEAR; i++) {
			ano.put(String.valueOf(i), i);
		}
	}
}
