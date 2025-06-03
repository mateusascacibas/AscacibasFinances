package com.finances.AscacibasFinances.comuns;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class Utils {

	public LocalDateTime convertToLocalDateTime(Date date) {
	    return date.toInstant()
	               .atZone(ZoneId.systemDefault())
	               .toLocalDateTime();
	}

}
