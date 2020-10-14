package com.coder_rangers.mobius_api.error.handler

import com.coder_rangers.mobius_api.error.exceptions.APIException
import com.coder_rangers.mobius_api.error.exceptions.BadRequestException
import com.coder_rangers.mobius_api.error.exceptions.InternalServerErrorException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.exc.MismatchedInputException
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.apache.commons.lang3.exception.ExceptionUtils
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    private fun logException(exception: Exception) {
        logger.error(exception.message, exception)
    }

    private fun getExceptionParameter(e: JsonMappingException): String =
        e.path.joinToString(".") {
            if (it.index >= 0)
                "[${it.index}]"
            else it.fieldName
        }

    @ExceptionHandler(APIException::class)
    fun handleAPIException(exception: APIException): ResponseEntity<Any> {
        logException(exception)

        return ResponseEntity.status(
            exception.httpStatus
        ).body(
            mapOf(
                "message" to exception.message,
                "statusCode" to exception.httpStatus.value(),
                "errorCode" to exception.httpStatus.reasonPhrase,
                "originalException" to ExceptionUtils.getRootCauseMessage(exception),
                "stackTrace" to ExceptionUtils.getStackFrames(exception).map { it.replace("\t", "") },
                "requestId" to exception.requestId,
                "timestamp" to LocalDateTime.now().toString()
            )
        )
    }

    private fun handleBadRequestException(
        message: String,
        exception: Exception
    ): ResponseEntity<Any> = handleAPIException(
        BadRequestException(message, exception)
    )

    override fun handleHttpMessageNotReadable(
        ex: HttpMessageNotReadableException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> = handleBadRequestException(
        ex.message ?: "error in reading body",
        ex
    )

    @ExceptionHandler(MissingKotlinParameterException::class)
    fun handleMissingKotlinParameterException(missingKotlinParameterException: MissingKotlinParameterException): ResponseEntity<Any> =
        handleBadRequestException(
            "key [${getExceptionParameter(missingKotlinParameterException)}] not found",
            missingKotlinParameterException
        )

    @ExceptionHandler(MismatchedInputException::class)
    fun handleMismatchedInputException(mismatchedInputException: MismatchedInputException): ResponseEntity<Any> =
        handleBadRequestException(
            "type mismatch for key [${getExceptionParameter(mismatchedInputException)}]",
            mismatchedInputException
        )

    @ExceptionHandler(JsonProcessingException::class)
    fun handleJsonProcessingException(jsonProcessingException: JsonProcessingException): ResponseEntity<Any> =
        handleBadRequestException(
            "error in parsing body",
            jsonProcessingException
        )

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> = handleBadRequestException(
        ex.message,
        ex
    )

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(constraintViolationException: ConstraintViolationException): ResponseEntity<Any> =
        handleBadRequestException(
            constraintViolationException.message ?: "Unrecognized constraint violation",
            constraintViolationException
        )

    @ExceptionHandler(Exception::class)
    fun handleGlobalException(exception: Exception): ResponseEntity<Any> = handleInternalServerErrorException(exception)

    private fun handleInternalServerErrorException(exception: Exception): ResponseEntity<Any> = handleAPIException(
        InternalServerErrorException(
            exception.message ?: "Something went wrong while processing your request",
            exception
        )
    )
}
