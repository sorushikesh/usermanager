package com.bilsora.usermanager.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

/**
 * Exception thrown when a requested resource already exists. Encapsulates ProblemDetail along with
 * an error code and optional arguments for message localization.
 */
@Getter
public class AlreadyExistsException extends ErrorResponseException {

  private final String errorCode;
  private final Object[] args;

  private AlreadyExistsException(String message, String errorCode, Object[] args) {
    super(HttpStatus.CONFLICT, createProblemDetail(message, errorCode, args), null);
    this.errorCode = errorCode;
    this.args = args;
  }

  /**
   * Factory method to create AlreadyExistsException.
   *
   * @param message Error message
   * @param errorCode Custom error code
   * @param args Optional arguments for localization
   * @return AlreadyExistsException
   */
  public static AlreadyExistsException of(String message, String errorCode, Object[] args) {
    return new AlreadyExistsException(message, errorCode, args);
  }

  private static ProblemDetail createProblemDetail(
      String message, String errorCode, Object[] args) {
    ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
    problemDetail.setTitle("Resource Already Exists");
    problemDetail.setDetail(message);
    problemDetail.setProperty("errorCode", errorCode);
    problemDetail.setProperty("arguments", args);
    return problemDetail;
  }
}
