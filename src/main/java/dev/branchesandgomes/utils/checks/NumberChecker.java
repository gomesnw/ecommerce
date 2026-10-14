package dev.branchesandgomes.utils.checks;

public class NumberChecker {
    public static boolean isNumber(String value) {
        return value != null && !value.isEmpty() && value.matches("\\d+");
    }
}
