package io.cdap.wrangler.api.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeDuration {
    private static final Pattern PATTERN = Pattern.compile("([0-9.]+)\\s*(ms|s|m|h)");

    public static double toMilliseconds(String input) throws IllegalArgumentException {
        Matcher matcher = PATTERN.matcher(input.trim().toLowerCase());
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid time duration: " + input);
        }

        double value = Double.parseDouble(matcher.group(1));
        String unit = matcher.group(2);

        return switch (unit) {
            case "ms" -> value;
            case "s" -> value * 1000;
            case "m" -> value * 60 * 1000;
            case "h" -> value * 60 * 60 * 1000;
            default -> throw new IllegalArgumentException("Unknown time unit: " + unit);
        };
    }
}
