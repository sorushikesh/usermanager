package com.bilsora.usermanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

/**
 * Exception thrown when a requested resource already exists. Encapsulates ProblemDetail along with
 * an error code and optional arguments for message localization.
 */
public class AlreadyExistsException extends ErrorResponseException {

  /**
   * Constructs a new AlreadyExistsException with the given status, problem detail, error code, and
   * arguments.
   *
   * @param statusCode the HTTP status code
   * @param problemDetail the problem detail object
   * @param errorCode the error code for localization
   * @param errorMessageArguments the arguments for the error message, may be null
   */
  public AlreadyExistsException(HttpStatusCode statusCode, ProblemDetail problemDetail, String errorCode,
      Object[] errorMessageArguments) {
    super(statusCode, validateProblemDetail(problemDetail), null, errorCode, errorMessageArguments);
  }

  /**
   * Constructs a new AlreadyExistsException with HTTP 409 (Conflict) by default.
   *
   * @param problemDetail the problem detail object
   * @param errorCode the error code for localization
   * @param errorMessageArguments the arguments for the error message, may be null
   */
  public AlreadyExistsException(ProblemDetail problemDetail, String errorCode,
      Object[] errorMessageArguments) {
    this(HttpStatus.CONFLICT, problemDetail, errorCode, errorMessageArguments);
  }

  /**
   * Static factory method to create an AlreadyExistsException easily.
   *
   * @param message the error message
   * @param errorCode the error code
   * @param errorMessageArguments List of arguments
   * @return the AlreadyExistsException
   */
  public static AlreadyExistsException of(String message, String errorCode,
      Object[] errorMessageArguments) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, message);
    problemDetail.setTitle("Resource Already Exists");
    return new AlreadyExistsException(problemDetail, errorCode, errorMessageArguments);
  }

  private static ProblemDetail validateProblemDetail(ProblemDetail problemDetail) {
    if (problemDetail == null) {
      throw new IllegalArgumentException("ProblemDetail must not be null");
    }
    return problemDetail;
  }
}
