package dev.branchesandgomes.utils.formatter;

public class NumberFormatter {
    public static String onlyNumbers(String value) {
        return value.replaceAll("\\D", "");
    }
}
