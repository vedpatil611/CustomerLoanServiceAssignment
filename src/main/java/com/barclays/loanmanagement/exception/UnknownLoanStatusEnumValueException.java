package com.barclays.loanmanagement.exception;

public class UnknownLoanStatusEnumValueException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UnknownLoanStatusEnumValueException(String message) {
		super(message);
	}
}
