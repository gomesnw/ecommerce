package dev.branchesandgomes.utils.validator;

public class EmailValidator {
    public static boolean isValid(String email) {
        if (email == null || email.isBlank()) {
            return false;
        }

        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        return email.matches(regex);
    }
}
