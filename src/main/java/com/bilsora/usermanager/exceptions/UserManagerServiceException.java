package com.bilsora.usermanager.exceptions;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

public class UserManagerServiceException extends ErrorResponseException {

  public UserManagerServiceException(HttpStatusCode statusCode, ProblemDetail problemDetail,
      String errorCode, Object[] errorMessageArgument) {
    super(statusCode, problemDetail, null, errorCode, errorMessageArgument);
  }
}
