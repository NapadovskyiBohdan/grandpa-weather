package com.grandpaweather.error;

import com.grandpaweather.domain.response.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionFilter extends ResponseEntityExceptionHandler {


    @ExceptionHandler({BaseNotFoundException.class})
    public ResponseEntity<Object> handleAllNotFoundEntity(BaseNotFoundException ex, WebRequest request) {
        GenericResponse error = GenericResponse.buildError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), null, request.getContextPath());
        return new ResponseEntity<>(error, error.httpStatus);
    }

}
