package org.example.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Some fields must be filled")
public class MissingFieldsException extends RuntimeException {
}
