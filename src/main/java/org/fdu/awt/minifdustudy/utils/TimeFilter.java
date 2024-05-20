package org.fdu.awt.minifdustudy.utils;

/**
 * @author Violette
 * @date 2024/5/20 16:30
 */
public enum TimeFilter {
    TODAY,
    LAST_WEEK,
    LAST_MONTH;

    public static TimeFilter from(String value) {
        try {
            return TimeFilter.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value for TimeFilter: " + value);
        }
    }
}
