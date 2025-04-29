package com.bilsora.usermanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.ErrorResponseException;

/**
 * Exception thrown when a requested resource is not found. Encapsulates ProblemDetail along with an
 * error code and optional arguments for message localization.
 */
public class NotFoundException extends ErrorResponseException {

  /**
   * Constructs a new NotFoundException with the given status, problem detail, error code, and
   * arguments.
   *
   * @param statusCode the HTTP status code
   * @param problemDetail the problem detail object
   * @param errorCode the error code for localization
   * @param errorMessageArguments the arguments for the error message, may be null
   */
  public NotFoundException(
      HttpStatusCode statusCode,
      ProblemDetail problemDetail,
      String errorCode,
      Object[] errorMessageArguments) {

    super(statusCode, validateProblemDetail(problemDetail), null, errorCode, errorMessageArguments);
  }

  /**
   * Constructs a new NotFoundException with HTTP 404 (Not Found) by default.
   *
   * @param problemDetail the problem detail object
   * @param errorCode the error code for localization
   * @param errorMessageArguments the arguments for the error message, may be null
   */
  public NotFoundException(
      ProblemDetail problemDetail, String errorCode, Object[] errorMessageArguments) {

    this(HttpStatus.NOT_FOUND, problemDetail, errorCode, errorMessageArguments);
  }

  /**
   * Static factory method to create a NotFoundException easily.
   *
   * @param message the error message
   * @param errorCode the error code
   * @param errorMessageArguments List of arguments
   * @return the NotFoundException
   */
  public static NotFoundException of(
      String message, String errorCode, Object[] errorMessageArguments) {
    ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, message);
    return new NotFoundException(problemDetail, errorCode, errorMessageArguments);
  }

  private static ProblemDetail validateProblemDetail(ProblemDetail problemDetail) {
    if (problemDetail == null) {
      throw new IllegalArgumentException("ProblemDetail must not be null");
    }
    return problemDetail;
  }
}
