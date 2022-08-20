package com.barclays.loanmanagement.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.barclays.loanmanagement.utils.LoanStatus;

/**
 * Customer - Customer Entity mapped to Customer table in database
 * @author Ved
 *
 */
@Entity
public class Loan {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer loanId;
	Double amount;
	@Column(name = "issue_date")
	LocalDate date;
	
	@ManyToOne
	@JoinColumn(name = "cust_id", unique = true)
	Customer customer;
	@Enumerated(EnumType.STRING)
	LoanStatus status;
	
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
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public LoanStatus getStatus() {
		return status;
	}
	
	public void setStatus(LoanStatus status) {
		this.status = status;
	}
	
}
