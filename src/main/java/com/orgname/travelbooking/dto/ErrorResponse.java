package com.orgname.travelbooking.dto;

import lombok.Builder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Builder
public record ErrorResponse(String errorMessage, String path, HttpStatus errorCode, LocalDateTime errorTime) {
}
