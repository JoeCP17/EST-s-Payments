package com.example.estpayments.web.dto

import org.springframework.http.HttpStatus

data class ExceptionResponseDto(
    val code: Int,
    val message: String
) {
    companion object {
        fun of(httpStatus: HttpStatus, message: String): ExceptionResponseDto =
            ExceptionResponseDto(
                code = httpStatus.value(),
                message = message
            )
    }
}
