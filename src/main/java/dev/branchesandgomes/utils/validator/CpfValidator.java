package dev.branchesandgomes.utils.validator;

public class CpfValidator {
    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        // Remove tudo que não for dígito
        cpf = cpf.replaceAll("\\D", "");

        // CPF deve conter 11 dígitos
        if (cpf.length() != 11) {
            return false;
        }

        // Rejeita CPFs com todos os dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int soma = 0;

        // Valida o primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * (10 - i);
        }

        int resto = (soma * 10) % 11;
        int digito1 = (resto == 10) ? 0 : resto;

        if (digito1 != cpf.charAt(9) - '0') {
            return false;
        }

        soma = 0;

        // Valida o segundo dígito verificador
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * (11 - i);
        }

        resto = (soma * 10) % 11;
        int digito2 = (resto == 10) ? 0 : resto;

        return digito2 == cpf.charAt(10) - '0';
    }

}
