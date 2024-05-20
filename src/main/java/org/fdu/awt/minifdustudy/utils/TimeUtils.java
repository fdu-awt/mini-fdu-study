package org.fdu.awt.minifdustudy.utils;

import java.sql.Timestamp;
import java.util.Calendar;

/**
 * @author Violette
 * @date 2024/5/13 23:43
 */
public class TimeUtils {

    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }

    public static Timestamp getFromTimeBasedOnFilter(TimeFilter filter) {
        Calendar cal = Calendar.getInstance();
        resetTime(cal);

        switch (filter) {
            case TODAY -> {
                // 返回今天00:00:00的时间戳
                return new Timestamp(cal.getTimeInMillis());
            }
            case LAST_WEEK -> {
                // 返回上周同一天00:00:00的时间戳
                cal.add(Calendar.WEEK_OF_MONTH, -1);
                return new Timestamp(cal.getTimeInMillis());
            }
            case LAST_MONTH -> {
                // 返回上个月同一天00:00:00的时间戳
                cal.add(Calendar.MONTH, -1);
                return new Timestamp(cal.getTimeInMillis());
            }
            default -> throw new IllegalArgumentException("Invalid time filter");
        }
    }

    private static void resetTime(Calendar cal) {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
    }
}
