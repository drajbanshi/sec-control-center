package com.freddiemac.scc.utils

import java.text.DateFormat;
import java.text.SimpleDateFormat

class DateUtils {
	
	static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd")
	
	static boolean isPastDate(String date) {
		if(!date || date.isEmpty()) {
			return false
		}
		
		Date dt = formatter.parse(date)
		
		Calendar c = Calendar.getInstance()
		c.setTime(dt)
		c.set(Calendar.HOUR, 23)
		c.set(Calendar.MINUTE, 59)
		return c.before(new Date())
	}

}
