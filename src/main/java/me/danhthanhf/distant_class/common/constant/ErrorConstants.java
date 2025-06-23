package me.danhthanhf.distant_class.common.constant;


public final class ErrorConstants {
    // Common error messages
    public static final String ERR_INTERNAL_SERVER_ERROR = "An internal server error occurred. Please try again later.";
    public static final String ERR_ENTITY_NOT_FOUND = "The requested entity was not found.";
    public static final String ERR_BUSINESS_LOGIC = "Business logic error occurred.";
    public static final String ERR_VALIDATION_FAILED = "Validation failed.";
    public static final String ERR_ACCESS_DENIED = "Access denied.";
    public static final String ERR_UNAUTHORIZED = "Unauthorized.";
    public static final String ERR_UTILITY_CLASS = "This is a utility class and cannot be instantiated";
    public static final String ERR_INVALID_REQUEST_BODY = "Invalid request body. Please check your input and try again.";

    // Jwt error messages
    public static final String ERR_TOKEN_EXPIRED = "Token has expired.";
    public static final String ERR_TOKEN_INVALID = "The provided token is invalid. ";
    public static final String ERR_TOKEN_MISSING = "Token is missing.";
    public static final String ERR_TOKEN_SIGNATURE_INVALID = "Token signature verification failed. ";
    public static final String ERR_GENERIC_ERROR = "An unexpected error occurred.";

    // Validation error messages
    public static final String ERR_NOT_BLANK = "This field cannot be blank.";
    public static final String ERR_NOT_EMPTY = "This field cannot be empty.";
    public static final String ERR_NOT_NULL = "This field is required.";
    public static final String ERR_EMAIL_INVALID = "Please enter a valid email address.";
    public static final String ERR_PASSWORD_FORMAT = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.";
    public static final String ERR_PASSWORD_LENGTH = "Password must be between 8 and 32 characters long.";
    public static final String ERR_VERIFY_TOKEN_REQUIRED = "Verification token is required.";

    // User error messages
    public static final String ERR_EMAIL_ALREADY_EXISTS = "An account with this email already exists. Please use a different email.";
    public static final String ERR_USER_NOT_FOUND = "User not found.";

    // Mail error messages
    public static final String ERR_MAIL_SEND_FAILED = "Failed to send email.";
    public static final String ERR_MAIL_SEND_HTML_FAILED = "Error while building HTML content for verification email.";

    // Code error messages
    public static final String ERR_CODE_EXPIRED = "The verification code has expired.";
    public static final String ERR_CODE_INVALID = "The provided verification code is invalid.";

    private ErrorConstants() {
        throw new IllegalStateException("This is a constant class and cannot be instantiated");
    }

}
