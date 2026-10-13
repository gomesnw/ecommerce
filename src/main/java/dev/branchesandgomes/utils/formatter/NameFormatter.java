package dev.branchesandgomes.utils.formatter;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NameFormatter {
    public static String capitalize(String value) {
        if (value == null || value.isBlank()) {
            return value;
        }

        return Arrays.stream(value.trim().toLowerCase().split("\\s+"))
                .map(palavra -> palavra.substring(0, 1).toUpperCase() + palavra.substring(1))
                .collect(Collectors.joining(" "));
    }

}
