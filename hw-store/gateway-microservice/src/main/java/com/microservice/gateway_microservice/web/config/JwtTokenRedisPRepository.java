package com.microservice.gateway_microservice.web.config;

import com.microservice.gateway_microservice.web.config.utils.ConstSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class JwtTokenRedisPRepository {

    private static final String REDIS_KEY_PREFIX = "jwt:";
    private static final long EXPIRATION_TIME_IN_MILLISECONDS = ConstSecurity.JWT_EXPIRATION_TOKEN;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void storeToken(String username, String browserId, String token) {
        String redisKey = REDIS_KEY_PREFIX + username + ":" + browserId;
        redisTemplate.opsForValue().set(redisKey, token, EXPIRATION_TIME_IN_MILLISECONDS, TimeUnit.MILLISECONDS);
    }

    public boolean isTokenValid(String username, String browserId, String token) {
        String redisKey = REDIS_KEY_PREFIX + username + ":" + browserId;
        String storedToken = redisTemplate.opsForValue().get(redisKey);
        return storedToken != null && storedToken.equals(token);
    }

    public void deleteToken(String username, String browserId) {
        String redisKey = REDIS_KEY_PREFIX + username + ":" + browserId;
        redisTemplate.delete(redisKey);
    }
}
