package com.barclays.loanmanagement.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.barclays.loanmanagement.utils.ErrorInfo;

/**
 * Automatically Catch all exceptions thrown in controller
 * @author galactagon
 *
 */
@RestControllerAdvice
public class LoanManagementExceptionHandler {
	
	/**
	 * exceptionHandler - Catch any uncaught exceptions
	 * @param exception
	 * @return ErrorInfo response 
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage("Request cannot be processed: " + exception.getMessage());
		error.setErrorCode(HttpStatus.ACCEPTED.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * barclaysBankExceptionHandler - Catch Barclays exception
	 * @param exception
	 * @return ErrorInfo response
	 */
	@ExceptionHandler(BarclaysBankException.class)
	public ResponseEntity<ErrorInfo> barclaysBankExceptionHandler(BarclaysBankException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * unknonwLoanStatusEnumValueExceptionHandler - Catch UnknownLoanStatusEnumValueException exception
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(UnknownLoanStatusEnumValueException.class)
	public ResponseEntity<ErrorInfo> unknonwLoanStatusEnumValueExceptionHandler(UnknownLoanStatusEnumValueException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
	
}
