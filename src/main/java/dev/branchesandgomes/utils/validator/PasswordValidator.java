package dev.branchesandgomes.utils.validator;

public class PasswordValidator {
    public static boolean isValid(String password) {
        if (password == null || password.isEmpty()) {
            return false;
        }

        return password.length() >= 8
                && password.matches(".*[A-Z].*")
                && password.matches(".*[a-z].*")
                && password.matches(".*\\d.*")
                && password.matches(".*[^a-zA-Z0-9].*");
    }
}
