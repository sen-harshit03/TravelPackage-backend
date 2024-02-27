package com.orgname.travelbooking.exceptions;

import com.orgname.travelbooking.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import software.amazon.awssdk.awscore.exception.AwsServiceException;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionalHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(
            final WebRequest webRequest, final Exception exception ) {
        ErrorResponse response = ErrorResponse.builder()
                .path(webRequest.getDescription(false))
                .errorMessage(exception.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorTime(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(
            final WebRequest webRequest, final Exception exception ) {
        ErrorResponse response = ErrorResponse.builder()
                .path(webRequest.getDescription(false))
                .errorMessage(exception.getMessage())
                .errorCode(HttpStatus.NOT_FOUND)
                .errorTime(LocalDateTime.now())
                .build();
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }

    @ExceptionHandler(NotEnoughCapacityException.class)
    public ResponseEntity<ErrorResponse> handleNotEnoughCapacityException(
            final WebRequest webRequest, final Exception exception ) {
        ErrorResponse response = ErrorResponse.builder()
                .path(webRequest.getDescription(false))
                .errorMessage(exception.getMessage())
                .errorCode(HttpStatus.BAD_REQUEST)
                .errorTime(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

    @ExceptionHandler(S3Exception.class)
    public ResponseEntity<ErrorResponse> handleS3Exception(
            final WebRequest webRequest, final Exception exception ) {
        ErrorResponse response = ErrorResponse.builder()
                .path(webRequest.getDescription(false))
                .errorMessage(exception.getMessage())
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorTime(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @ExceptionHandler(AwsServiceException.class)
    public ResponseEntity<ErrorResponse> handleAwsServiceException(
            final WebRequest webRequest, final Exception exception ) {
        ErrorResponse response = ErrorResponse.builder()
                .path(webRequest.getDescription(false))
                .errorMessage(exception.getMessage())
                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .errorTime(LocalDateTime.now())
                .build();

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
