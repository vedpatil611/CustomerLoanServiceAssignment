package com.barclays.loanmanagement.exception;

/**
 * This exception is thrown if invalid enum value as string is passed for loan status
 * @author Ved
 *
 */
public class UnknownLoanStatusEnumValueException extends Exception {

	private static final long serialVersionUID = 1L;

	public UnknownLoanStatusEnumValueException(String message) {
		super(message);
	}
}
