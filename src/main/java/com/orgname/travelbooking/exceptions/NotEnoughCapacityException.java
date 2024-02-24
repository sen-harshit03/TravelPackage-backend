package com.orgname.travelbooking.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotEnoughCapacityException extends RuntimeException {
    public NotEnoughCapacityException(final String message) {
        super(message);
    }
}
