package dev.branchesandgomes.exception;

import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InternalServerErrorException extends ResponseStatusException {
    public InternalServerErrorException(@Nullable String reason) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, reason);
    }
}
