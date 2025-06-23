package me.danhthanhf.distant_class.common.constant;

import java.time.temporal.ChronoUnit;

public final class AppConstants {
    // Common constants
    public static final int VERIFY_EMAIL_CODE_LENGTH = 6; // Length of the verification code
    public static final int VERIFY_CODE_EXPIRY_MINUTES = 1; // Expiry time for verification code in minutes
    public static final ChronoUnit VERIFY_CODE_EXPIRY_TIME_UNIT = ChronoUnit.MINUTES;

    // Header constants
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String CONTENT_TYPE_JSON = "application/json";
    public static final String CHARSET_UTF_8 = "UTF-8";
    // Qualifiers constants
    public static final String REDIS_TEMPLATE_STRING = "redisTemplateString";
    public static final String REDIS_TEMPLATE_OBJECT = "redisTemplateObject";
    public static final String REDIS_TEMPLATE_GENERIC = "redisTemplateGeneric";
    // Regex constants
    public static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,32}$"; // At least one lowercase, one uppercase, one digit, and 8-32 characters long
    // Mail constants
    public static final String USERNAME_VARIABLE = "username";
    public static final String VERIFY_LINK_VARIABLE = "verifyLink";
    public static final String TEMPLATE_NAME = "verify_email"; // name of the html file in resources/templates
    public static final String VERIFY_EMAIL_SUBJECT = "Verify your email address";

    // API constants
    public static final String API_BASE_PATH = "/api/v1"; // Base path for the API
    public static final String API_AUTH_VERIFY_EMAIL = API_BASE_PATH + "/auth/verify-email"; // Endpoint for email verification

    // Redis constants
    public static final String REDIS_VERIFY_TOKEN_PREFIX = "verify_token:"; // Prefix for verification email keys in Redis
    public static final String REDIS_ACCESS_TOKEN_PREFIX = "access_token:"; // Prefix for access tokens in Redis

    private AppConstants() {
        throw new IllegalStateException(ErrorConstants.ERR_UTILITY_CLASS);
    }
}
