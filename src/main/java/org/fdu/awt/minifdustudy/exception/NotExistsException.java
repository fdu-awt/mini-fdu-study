package org.fdu.awt.minifdustudy.exception;

/**
 * @author Violette
 * @date 2024/5/13 19:02
 */
public class NotExistsException extends Exception {
    public NotExistsException(String message) {
        super(message);
    }

    public static NotExistsException NotExistsEntity(String entityClassName, String name) {
        return new NotExistsException(String.format("不存在name为%s的%s", name, entityClassName));
    }
}
