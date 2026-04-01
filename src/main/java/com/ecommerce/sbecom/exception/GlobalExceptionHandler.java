package com.ecommerce.sbecom.exception;

import com.ecommerce.sbecom.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse > handleInvalidDetailsSpring(MethodArgumentNotValidException ex){
        StringBuilder message = new StringBuilder();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                message.append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append(", ")
        );

        ErrorResponse errorResponse = new ErrorResponse(
                message.toString(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDATION_FAILED"
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerDeatilsInvaildException.class)
    public ResponseEntity<ErrorResponse> handleInvalidDetails(CustomerDeatilsInvaildException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourseNotFoundException.class)
    public ResponseEntity<ErrorResponse> resourseNotFoundException(ResourseNotFoundException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> apiExceptionException(ApiException ex){
        ErrorResponse errorResponse=new ErrorResponse();
        errorResponse.setErrorCode(HttpStatus.CONFLICT.toString());
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(HttpStatus.CONFLICT.value());

        return new ResponseEntity<>(errorResponse,HttpStatus.CONFLICT);
    }
}
