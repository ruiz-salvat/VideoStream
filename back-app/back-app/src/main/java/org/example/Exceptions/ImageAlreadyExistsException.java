package org.example.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "An image with this name already exists")
public class ImageAlreadyExistsException extends RuntimeException {
}