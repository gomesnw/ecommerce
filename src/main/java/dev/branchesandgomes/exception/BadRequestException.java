package dev.branchesandgomes.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class BadRequestException extends ResponseStatusException {
    public BadRequestException(@Nullable String reason) {
        super(HttpStatus.BAD_REQUEST, reason);
    }
}
