package me.danhthanhf.distant_class.common.enums;

import lombok.Getter;

/**
 * Enum representing different types of tokens used in redis, etc.
 * <p>
 * This enum defines three types of tokens:
 * - ACCESS_TOKEN: Used for user authentication and authorization.
 * - VERIFY_TOKEN: Used for email verification processes.
 * - REFRESH_TOKEN: Used to obtain new access tokens without requiring the user to log in again.
 * - Value is prefix in Redis, etc.
 * </p>
 */

@Getter
public enum TokenTypeEnum {
    ACCESS_TOKEN("access_token"), VERIFY_TOKEN("verify_token"), REFRESH_TOKEN("refresh_token");

    private final String value;

    TokenTypeEnum(String value) {
        this.value = value;
    }
}
