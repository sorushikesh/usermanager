package com.bilsora.usermanager.exceptions;

import static com.bilsora.usermanager.constants.FieldConstant.COLON;
import static com.bilsora.usermanager.constants.FieldConstant.ERROR_CODE;

import jakarta.servlet.http.HttpServletRequest;
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
        messageSource.getMessage(
            errorCode, ex.getDetailMessageArguments(), LocaleContextHolder.getLocale());

    problemDetail.setProperty(ERROR_CODE, errorCode + COLON + errorMessage);

    return problemDetail;
  }
}
