package dev.branchesandgomes.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFountException extends ResponseStatusException {
    public NotFountException(@Nullable String reason) {
        super(HttpStatus.NOT_FOUND, reason);
    }
}
