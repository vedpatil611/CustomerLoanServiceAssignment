package com.barclays.loanmanagement.exception;

/**
 * BarclaysBankException - Custom exception class
 * @author Ved
 *
 */
public class BarclaysBankException extends Exception {
	private static final long serialVersionUID = 1L;

	public BarclaysBankException(String message) {
		super(message);
	}
}
