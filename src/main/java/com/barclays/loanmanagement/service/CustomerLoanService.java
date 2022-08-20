package com.barclays.loanmanagement.service;

import com.barclays.loanmanagement.dto.LoanDTO;
import com.barclays.loanmanagement.exception.BarclaysBankException;

/**
 * CustomerLoanService - Customer Loan Service api
 * @author Ved
 *
 */
public interface CustomerLoanService {
	/**
	 * getLoanDetails - Get loan details
	 * @param loanId - unique loan id
	 * @return LoanDTO
	 * @throws BarclaysBankException
	 */
	public LoanDTO getLoanDetails(Integer loanId) throws BarclaysBankException;
		
	/**
	 * addLoanAndCustomer - add new customer and loan to database
	 * @param loanDTO - LoanDTO object
	 * @return Id of newly added loan
	 * @throws BarclaysBankException
	 */
	public Integer addLoanAndCustomer(LoanDTO loanDTO) throws BarclaysBankException;
	
	/**
	 * sanctionLoanToExistingCustomer - Sanction new loan to an existing customer
	 * @param customerId - id of customer to whom loan is to be sanctioned
	 * @param loanDTO - loan details
	 * @return Id of newly added loan
	 * @throws BarclaysBankException
	 */
	public Integer sanctionLoanToExistingCustomer(Integer customerId,LoanDTO loanDTO) throws BarclaysBankException;
	
	/**
	 * deleteLoan - Delete loan from database
	 * @param loanId - unique loan id
	 * @throws BarclaysBankException
	 */
	public void deleteLoan(Integer loanId) throws BarclaysBankException;
	
	/**
	 * closeLoan - Close an open loan
	 * @param loanId - unique loan id
	 * @throws BarclaysBankException
	 */
	public void closeLoan(Integer loanId) throws BarclaysBankException;
}
