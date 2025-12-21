package org.serverest.factory;

import java.util.Random;

public class IdFactory {
    public static String criar(Integer length) {
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuvwxyz";
            Random random = new Random();
            StringBuilder sb = new StringBuilder(length);
            for (int i = 0; i < length; i++) {
                // Get a random index from the character set
                int randomIndex = random.nextInt(characters.length());
                // Append the character at that index to the StringBuilder
                sb.append(characters.charAt(randomIndex));
            }
            return sb.toString();
    }
}