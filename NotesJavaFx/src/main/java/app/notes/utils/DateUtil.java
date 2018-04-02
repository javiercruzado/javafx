package app.notes.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtil {

	public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

	private DateUtil() {
		throw new IllegalStateException("Utility class");
	}

	/***
	 * Format a Local time in the format yyyy-MM-dd HH:mm:ss
	 * 
	 * @param dateTime
	 *            date time to be formatted
	 * @return
	 */
	public static String formatDateLocalTime(LocalDateTime dateTime) {
		return dateTime.format(formatter);
	}

	/***
	 * Get the local time of a string that is format as "yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param datetime
	 * @return
	 */
	public static LocalDateTime stringToLocalDateTime(String dateTime) {
		return LocalDateTime.parse(dateTime, formatter);
	}

}
