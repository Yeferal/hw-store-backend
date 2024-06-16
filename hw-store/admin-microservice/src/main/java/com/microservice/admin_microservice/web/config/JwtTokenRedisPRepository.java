package com.microservice.admin_microservice.web.config;

import com.microservice.admin_microservice.web.config.utils.ConstSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class JwtTokenRedisPRepository {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String REDIS_KEY_PREFIX = "jwt:";

    public boolean isTokenValid(String username, String browserId, String token) {
        String redisKey = REDIS_KEY_PREFIX + username + ":" + browserId;
        String storedToken = redisTemplate.opsForValue().get(redisKey);
        return storedToken != null && storedToken.equals(token);
    }

}
