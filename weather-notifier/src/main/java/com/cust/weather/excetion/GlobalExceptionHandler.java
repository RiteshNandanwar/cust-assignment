package com.cust.weather.excetion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.cust.weather.response.ErrorMessage;
import com.cust.weather.utils.GenConstants;

@ControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * Handle generic exceptions (InternalServerError).
	 *
	 * @param ex the exception that was thrown
	 * @param request the web request during which the exception was thrown
	 * @return ResponseEntity with custom error message and HTTP status 500
	 */
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorMessage> handleInternalServerError(Exception ex, WebRequest request) {
		// Create a custom response body
		ErrorMessage errorResponse = new ErrorMessage();
		errorResponse.setErrorId(GenConstants.ERROR_CODE_1002);
		errorResponse.setErrorMessage(ex.getMessage());

		// Return ResponseEntity with custom message and HTTP status 500
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handle user-related exceptions (BadRequest).
	 *
	 * @param ex the exception that was thrown
	 * @param request the web request during which the exception was thrown
	 * @return ResponseEntity with custom error message and HTTP status 400
	 */
	@ExceptionHandler({UserNotFoundException.class, PostalCodeNotFoundException.class})
	public ResponseEntity<ErrorMessage> handleEntityRelatedException(Exception ex, WebRequest request) {
		// Create a custom response body
		ErrorMessage errorResponse = new ErrorMessage();
		errorResponse.setErrorId(GenConstants.ERROR_CODE_1002);
		errorResponse.setErrorMessage(ex.getMessage());

		// Return ResponseEntity with custom message and HTTP status 400
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
}