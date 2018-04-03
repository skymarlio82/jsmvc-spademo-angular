
package com.demo.spa.boot.mvc.rest.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleFormater {

	private final static String SHORT_SIMPLE_DATE_FORMATE = "MM/dd/yyyy";
	private final static SimpleDateFormat sdf             = new SimpleDateFormat(SHORT_SIMPLE_DATE_FORMATE);

	public final static String simpleFormate(Date date) {
		return sdf.format(date);
	}

	public final static Date simpleFormate(String date) {
		Date d = null;
		try {
			d = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
	}

}
