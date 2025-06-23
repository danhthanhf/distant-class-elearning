package me.danhthanhf.distant_class.common.util;

import lombok.experimental.UtilityClass;

import java.security.SecureRandom;

@UtilityClass
public class CommonUtil {
    public String generateRandomCode(int length) {
        StringBuilder code = new StringBuilder(length);
        SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int digit = random.nextInt(10); // Generates a random digit from 0 to 9
            code.append(digit);
        }
        return code.toString();
    }
}
