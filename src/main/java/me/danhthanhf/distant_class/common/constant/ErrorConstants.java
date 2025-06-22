package me.danhthanhf.distant_class.common.constant;


public final class ErrorConstants {
    // Common error messages
    public static final String ERR_INTERNAL_SERVER_ERROR = "An internal server error occurred. Please try again later.";
    public static final String ERR_ENTITY_NOT_FOUND = "The requested entity was not found.";
    public static final String ERR_BUSINESS_LOGIC = "Business logic error occurred.";
    public static final String ERR_VALIDATION_FAILED = "Validation failed. Please check your input.";

    public static final String ERR_UTILITY_CLASS = "This is a utility class and cannot be instantiated";
    // Jwt error messages
    public static final String ERR_TOKEN_EXPIRED = "Your session has expired. Please log in again.";
    public static final String ERR_TOKEN_INVALID = "The provided token is invalid. Please try again.";
    public static final String ERR_TOKEN_MISSING = "Authentication token is missing. Please provide a valid token.";
    public static final String ERR_TOKEN_SIGNATURE_INVALID = "Token signature verification failed. Access denied.";
    public static final String ERR_GENERIC_ERROR = "An unexpected error occurred. Please try again later.";

    // Validation error messages
    public static final String ERR_NOT_BLANK = "This field cannot be blank.";
    public static final String ERR_NOT_EMPTY = "This field cannot be empty.";
    public static final String ERR_NOT_NULL = "This field is required.";
    public static final String ERR_EMAIL_INVALID = "Please enter a valid email address.";
    public static final String ERR_PASSWORD_FORMAT = "Password must be at least 8 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.";
    public static final String ERR_PASSWORD_LENGTH = "Password must be between 8 and 32 characters long.";

    // User error messages
    public static final String ERR_EMAIL_ALREADY_EXISTS = "An account with this email already exists. Please use a different email.";

    private ErrorConstants() {
        throw new IllegalStateException("This is a constant class and cannot be instantiated");
    }

}
