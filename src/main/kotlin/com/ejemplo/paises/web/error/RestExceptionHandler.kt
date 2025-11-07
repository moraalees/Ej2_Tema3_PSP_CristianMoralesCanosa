package com.ejemplo.paises.web.error

import com.ejemplo.paises.service.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

data class ApiError(
    val status: Int,
    val error: String,
    val message: String?
)

@ControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFound(ex: NotFoundException): ResponseEntity<ApiError> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(ApiError(404, "NOT_FOUND", ex.message))

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(ex: IllegalArgumentException): ResponseEntity<ApiError> =
        ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(ApiError(400, "BAD_REQUEST", ex.message))

    @ExceptionHandler(MethodArgumentNotValidException::class, BindException::class)
    fun handleValidation(ex: Exception): ResponseEntity<ApiError> {
        val msg = when (ex) {
            is MethodArgumentNotValidException ->
                ex.bindingResult.fieldErrors.joinToString { "${it.field}: ${it.defaultMessage}" }
            is BindException ->
                ex.bindingResult.fieldErrors.joinToString { "${it.field}: ${it.defaultMessage}" }
            else -> ex.message ?: "Validation error"
        }
        return ResponseEntity.badRequest()
            .body(ApiError(400, "VALIDATION_ERROR", msg))
    }

    @ExceptionHandler(Exception::class)
    fun handleGeneral(ex: Exception): ResponseEntity<ApiError> =
        ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiError(500, "INTERNAL_SERVER_ERROR", ex.message))
}