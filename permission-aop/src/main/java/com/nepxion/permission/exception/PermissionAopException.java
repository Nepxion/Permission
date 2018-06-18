package com.nepxion.permission.exception;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

public class PermissionAopException extends RuntimeException {
    private static final long serialVersionUID = 36896074604270451L;

    public PermissionAopException() {
        super();
    }

    public PermissionAopException(String message) {
        super(message);
    }

    public PermissionAopException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionAopException(Throwable cause) {
        super(cause);
    }
}