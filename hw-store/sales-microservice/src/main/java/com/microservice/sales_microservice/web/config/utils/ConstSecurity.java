package com.microservice.sales_microservice.web.config.utils;

public class ConstSecurity {
    public static final String JWT_SIGN = "32478a195ee62a7f07fa691652581c412f3f884f20ffccd23dc3bfc5783b161b";
    public static final long JWT_EXPIRATION_TOKEN = 2_592_000_000L; // 30 Dias, donde 60*60*24*30 * 1000
    public static final long JWT_EXPIRATION_TOKEN_MILISECONDS = 2_592_000_000L; // 30 Dias, donde 60*60*24*30*1000

    public static final long JWT_EXPIRATION_TOKEN_MINUTES = 2_592_000L; // 30 Dias, donde 60*60*24*30
    public static final long JWT_EXPIRATION_TOKEN_REDIS_MINUTES = 43_200L; // 30 Dias, donde 1*60*24*30

    public static final long COOKIE_EXPIRATION_SID = 2_592_000L; // 30 Dias, donde 60*60*24*30
}
