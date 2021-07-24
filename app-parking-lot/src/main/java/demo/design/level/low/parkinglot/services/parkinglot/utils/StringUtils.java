package demo.design.level.low.parkinglot.services.parkinglot.utils;

import java.util.Locale;

public class StringUtils {

    public static String getFirstCharacterFromEachWordUpperCase(final String sentence) {
        return getFirstCharacterFromEachWord(sentence).toUpperCase();
    }

    public static String getFirstCharacterFromEachWord(final String sentence) {
        StringBuilder builder = new StringBuilder();
        char[] characters = sentence.toCharArray();
        builder.append(characters[0]);
        for (int i = 0; i < characters.length; i++) {
            if (((int) characters[i]) == 32  && i + 1 < characters.length) {
                builder.append(characters[i+1]);
            }
        }
        return builder.toString();
    }

}
