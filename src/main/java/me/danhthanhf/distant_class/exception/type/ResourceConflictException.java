package me.danhthanhf.distant_class.exception.type;

public class ResourceConflictException extends BusinessException {
    public ResourceConflictException(String message) {
        super(message);
    }

    public ResourceConflictException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
