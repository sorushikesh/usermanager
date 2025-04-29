package com.bilsora.usermanager.exceptions;

import static com.bilsora.usermanager.configuration.MessageSourceConfig.MESSAGE_SOURCE;
import static com.bilsora.usermanager.constants.FieldConstant.COLON;
import static com.bilsora.usermanager.constants.FieldConstant.ERROR_CODE;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@SuppressWarnings("unused")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

  private final @Qualifier(MESSAGE_SOURCE) MessageSource messageSource;

  public GlobalExceptionHandler(MessageSource messageSource) {
    this.messageSource = messageSource;
  }

  @ExceptionHandler(NotFoundException.class)
  public ProblemDetail handleNotFoundException(NotFoundException ex) {

    ProblemDetail problemDetail = ex.getBody();
    problemDetail.setStatus((HttpStatus) ex.getStatusCode());

    var errorCode = ex.getDetailMessageCode();
    var errorMessage = messageSource.getMessage(errorCode, ex.getDetailMessageArguments(),
        LocaleContextHolder.getLocale());

    problemDetail.setProperty(ERROR_CODE, errorCode + COLON + errorMessage);

    return problemDetail;
  }

  @ExceptionHandler(AlreadyExistsException.class)
  public ProblemDetail handleAlreadyExistsException(AlreadyExistsException ex) {

    ProblemDetail problemDetail = ex.getBody();
    problemDetail.setStatus((HttpStatus) ex.getStatusCode());

    var errorCode = ex.getDetailMessageCode();
    var errorMessage = messageSource.getMessage(errorCode, ex.getDetailMessageArguments(),
        LocaleContextHolder.getLocale());

    problemDetail.setProperty(ERROR_CODE, errorCode + COLON + errorMessage);

    return problemDetail;
  }
}
