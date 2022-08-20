package com.barclays.loanmanagement.constants;

public interface ApiMapping {
	public static final String LOAN_CONTROLLER_ROOT = "/api/loan";
	public static final String GET_LOAN = "/{loanId}";
	public static final String ADD_LOAN_AND_CUSTOMER = "/new";
	public static final String DELETE_LOAN = "/{loanId}";
	public static final String CLOSE_LOAN = "/close/{loanId}";
	
	public static final String CUSTOMER_CONTROLLER_ROOT = "/api/customer";
	public static final String SANCATION_NEW_LOAN = "/{customerId}";
}
