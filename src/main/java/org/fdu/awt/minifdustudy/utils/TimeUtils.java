package org.fdu.awt.minifdustudy.utils;

import java.sql.Timestamp;

/**
 * @author Violette
 * @date 2024/5/13 23:43
 */
public class TimeUtils {
    public static Timestamp now() {
        return new Timestamp(System.currentTimeMillis());
    }
}
