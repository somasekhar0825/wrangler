package io.cdap.wrangler.api.parser;

import java.util.Locale;

public class ByteSize extends Token {
    private final long bytes;

    public ByteSize(String value) {
        super(value);
        value = value.trim().toUpperCase(Locale.ROOT);
        if (value.endsWith("KB")) {
            bytes = (long)(Double.parseDouble(value.replace("KB", "")) * 1024);
        } else if (value.endsWith("MB")) {
            bytes = (long)(Double.parseDouble(value.replace("MB", "")) * 1024 * 1024);
        } else if (value.endsWith("GB")) {
            bytes = (long)(Double.parseDouble(value.replace("GB", "")) * 1024 * 1024 * 1024);
        } else if (value.endsWith("B")) {
            bytes = Long.parseLong(value.replace("B", ""));
        } else {
            throw new IllegalArgumentException("Unknown byte unit in value: " + value);
        }
    }

    public long getBytes() {
        return bytes;
    }
}
