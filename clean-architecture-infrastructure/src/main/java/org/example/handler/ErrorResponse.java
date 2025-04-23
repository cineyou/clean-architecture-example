package org.example.handler;

import org.springframework.http.HttpStatus;


public record ErrorResponse(String id, String message, HttpStatus status) {
}
