package com.barclays.loanmanagement.dto;

import java.time.LocalDate;

import com.barclays.loanmanagement.entity.Loan;
import com.barclays.loanmanagement.utils.LoanStatus;

/**
 * LoanDTO - Loan Data Object for interfacing Loan entity with client
 * @author Ved
 *
 */
public class LoanDTO {
	Integer loanId;
	Double amount;
	LocalDate date;
	CustomerDTO customer;
	LoanStatus status;
	
	/**
	 * Create new LoanDTO object
	 */
	public LoanDTO() {
	}
	
	/**
	 * Create new LoanDTO from Loan entity
	 * @param loan - loan entity object
	 * @param customerDTO - customer data transfer object
	 */
	public LoanDTO(Loan loan, CustomerDTO customerDTO) {
		this.loanId = loan.getLoanId();
		this.amount = loan.getAmount();
		this.date = loan.getDate();
		this.customer = customerDTO;
		this.status = loan.getStatus();
//		this.status = loan.getStatus().getValue();
	}
	
	/**
	 * toLoan - Convert to Loan Entity
	 * @return Loan entity object
	 */
	public Loan toLoanEntity() {
		Loan loan = new Loan();
		loan.setLoanId(loanId);
		loan.setAmount(amount);
		loan.setDate(date);
		loan.setStatus(status);
//		loan.setStatus(LoanStatus.of(status));
		
		return loan;
	}
	
	public Integer getLoanId() {
		return loanId;
	}
	
	public void setLoanId(Integer loadId) {
		this.loanId = loadId;
	}
	
	public Double getAmount() {
		return amount;
	}
	
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public CustomerDTO getCustomer() {
		return customer;
	}
	
	public void setCustomer(CustomerDTO customer) {
		this.customer = customer;
	}
	
	public LoanStatus getStatus() {
		return status;
	}
	
	public void setStatus(LoanStatus status) {
		this.status = status;
	}
}
