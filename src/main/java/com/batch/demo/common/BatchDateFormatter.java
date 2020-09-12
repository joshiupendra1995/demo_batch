package com.batch.demo.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * @author Upendra
 *
 */
public class BatchDateFormatter {

	public static LocalDate formatDate(String date) {

		return LocalDate.parse(date, DateTimeFormatter.ofPattern(BatchConstant.Dt_FORMAT));

	}

}
