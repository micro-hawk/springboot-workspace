package com.microhawk.student.utils;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class IDGenerator {

    private static final Random random = new Random();

    public static String generateCode(String name, int codeLength) {
        String firstChar = name.substring(0, 1);
        String remainingChars = name.substring(1)
            .replaceAll("[aeiouAEIOU\\s]", "");
        String code = firstChar + remainingChars;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (sb.indexOf(String.valueOf(c)) == -1) {
                sb.append(c);
            }
        }
        code = sb.toString().toUpperCase();
        while (code.length() < codeLength) {
            char c = (char) (random.nextInt(26) + 'A');
            if ("aeiouAEIOU".indexOf(c) == -1 && code.indexOf(c) == -1) {
                code += c;
            }
        }
        code = code.substring(0, codeLength);
        return code;
    }
}
