
package com.dronelab.droneserver.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateUtil {
    public static String formatTimestamp(LocalDateTime localDateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return localDateTime.format(formatter);

    }
}