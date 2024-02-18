package com.example.estpayments.web.exception

import com.example.estpayments.web.dto.ExceptionResponseDto
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(e: IllegalArgumentException): ExceptionResponseDto {
        return ExceptionResponseDto.of(HttpStatus.INTERNAL_SERVER_ERROR, e.message!!)
    }

}
