package com.user.micro.demo.application.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodingUtils {

    public static String encode(Long userId){
        return Base64.getEncoder().encodeToString(userId.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static Long decodeToLong(String encodedUserId){
        String decodedString = new String(Base64.getDecoder().decode(encodedUserId), StandardCharsets.UTF_8);
        return Long.parseLong(decodedString);
    }

    public static String encode(String value){
        return Base64.getEncoder().encodeToString(value.getBytes(StandardCharsets.UTF_8));
    }

    public static String decodeToString(String encodedValue){
        return new String(Base64.getDecoder().decode(encodedValue), StandardCharsets.UTF_8);
    }
}
