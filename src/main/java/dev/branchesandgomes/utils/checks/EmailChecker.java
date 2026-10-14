package dev.branchesandgomes.utils.checks;

public class EmailChecker {
    public static boolean isEmail(String value) {
        if (value == null || value.isBlank()) {
            return false;
        }

        return value.matches("^[\\w.%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }
}
