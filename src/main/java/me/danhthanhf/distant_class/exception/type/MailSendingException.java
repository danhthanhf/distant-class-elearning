package me.danhthanhf.distant_class.exception.type;

public class MailSendingException extends BusinessException {
    protected MailSendingException(String message) {
        super(message);
    }

    protected MailSendingException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
}
