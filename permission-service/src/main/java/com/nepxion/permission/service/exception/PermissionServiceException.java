package com.nepxion.permission.service.exception;

/**
 * <p>Title: Nepxion Permission</p>
 * <p>Description: Nepxion Permission</p>
 * <p>Copyright: Copyright (c) 2017-2050</p>
 * <p>Company: Nepxion</p>
 * @author Haojun Ren
 * @version 1.0
 */

public class PermissionServiceException extends RuntimeException {
    private static final long serialVersionUID = -4810827331430389419L;

    public PermissionServiceException() {
        super();
    }

    public PermissionServiceException(String message) {
        super(message);
    }

    public PermissionServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public PermissionServiceException(Throwable cause) {
        super(cause);
    }
}