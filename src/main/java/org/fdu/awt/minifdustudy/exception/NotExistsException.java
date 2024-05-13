package org.fdu.awt.minifdustudy.exception;

/**
 * @author Violette
 * @date 2024/5/13 19:02
 */
public class NotExistsException extends Exception {
    public NotExistsException(String message) {
        super(message);
    }

    public NotExistsException(String entityClassName, Long id) {
        super(String.format("不存在id为%s的%s", id, entityClassName));
    }

    public NotExistsException(String entityClassName, String name) {
        super(String.format("不存在name为%s的%s", name, entityClassName));
    }
}
