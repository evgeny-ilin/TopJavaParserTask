package util;

import static parser.Main.*;

public class ValidationUtil {
    public static boolean validateArgs(String[] args) {
        return args != null && args.length >= 3;
    }

    public static void checkParams() {
        if (url.isEmpty()) {
            throw new IllegalArgumentException("Url is empty");
        }
        if (!url.endsWith("=")) {
            url = url + "=";
        }
        if (start >= end) {
            throw new IllegalArgumentException("End value {} must be greater than Start value {}".formatted(end, start));
        }
    }
}
