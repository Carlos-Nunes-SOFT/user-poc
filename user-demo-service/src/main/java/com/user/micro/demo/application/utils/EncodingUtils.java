package com.user.micro.demo.application.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncodingUtils {

    public static String encode(Long userId){
        return Base64.getEncoder().encodeToString(userId.toString().getBytes(StandardCharsets.UTF_8));
    }

    public static Long decode(String encodedUserId){
        String decodedString = new String(Base64.getDecoder().decode(encodedUserId), StandardCharsets.UTF_8);
        return Long.parseLong(decodedString);
    }
}
