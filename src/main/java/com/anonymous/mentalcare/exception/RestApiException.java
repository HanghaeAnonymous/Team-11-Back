package com.anonymous.mentalcare.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class RestApiException {
    private String errorMessage;
    private HttpStatus httpStatus;
}
