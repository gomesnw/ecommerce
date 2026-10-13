package dev.branchesandgomes.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ForbiddenException extends ResponseStatusException {
    public ForbiddenException(@Nullable String reason) {
        super(HttpStatus.FORBIDDEN, reason);
    }
}
