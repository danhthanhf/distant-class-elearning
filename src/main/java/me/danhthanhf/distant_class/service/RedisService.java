package me.danhthanhf.distant_class.service;

import me.danhthanhf.distant_class.common.constant.AppConstants;
import me.danhthanhf.distant_class.common.enums.TokenTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    private final RedisTemplate<String, Object> genericTemplate;

    // Using ValueOperations to handle Redis type of key is String and value is Object
    private final ValueOperations<String, Object> valueOps;

    public RedisService(@Qualifier(AppConstants.REDIS_TEMPLATE_GENERIC) RedisTemplate<String, Object> genericTemplate) {
        this.genericTemplate = genericTemplate;
        this.valueOps = genericTemplate.opsForValue();
    }


    public void saveVerifyToken(String email, String token, long timeout) {
        // like: verify_token:email
        String key = AppConstants.REDIS_VERIFY_TOKEN_PREFIX + email;
        save(key, token, timeout, TimeUnit.SECONDS);
    }

    public String getToken(TokenTypeEnum prefix, String detail) {
        String key = prefix.getValue() + detail;
        return get(key, String.class);
    }

    public void saveAccessToken(Long userId, String token) {
        // like: access_token:userId
        String key = TokenTypeEnum.VERIFY_TOKEN.getValue() + userId;
        save(key, token);
    }

    public String get(String key) {
        return get(key, String.class);
    }

    // base methods for Redis operations
    public <T> void save(String key, T value) {
        valueOps.set(key, value);
    }

    public <T> void save(String key, T value, long timeout, TimeUnit timeUnit) {
        valueOps.set(key, value, timeout, timeUnit);
    }

    public <T> T get(String key, Class<T> type) {
        return type.cast(valueOps.get(key));
    }

    public void deleteToken(TokenTypeEnum prefix, String detail) {
        String key = prefix.getValue() + detail;
        genericTemplate.delete(key);
    }

}
