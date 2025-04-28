package com.bilsora.usermanager.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private final MessageSource messageSource;

  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(NotFoundException.class)
  public ProblemDetail handleNotFoundException(NotFoundException ex, HttpServletRequest request) {

    ProblemDetail problemDetail = ex.getBody();
    problemDetail.setStatus(HttpStatus.NOT_FOUND);

    var errorCode = ex.getDetailMessageCode();
    var errorMessage =
        Optional.of(
            messageSource.getMessage(
                errorCode, ex.getDetailMessageArguments(), LocaleContextHolder.getLocale()));

    problemDetail.setProperty("path", request.getRequestURI());
    problemDetail.setProperty("ErrorCode", errorCode + ":" + errorMessage);

    return problemDetail;
  }
}
