package com.microhawk.webissshdemo.service;

import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class HumanReadableIdGenerator {

    private static final char[] base62chars =
        "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
            .toCharArray();

    private static final Random random = new Random();

    public static String getBase62(int length) {
        var sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(base62chars[random.nextInt(62)]);
        }

        return sb.toString();
    }

    public static String getBase36(int length) {
        var sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(base62chars[random.nextInt(36)]);
        }

        return sb.toString();
    }

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
