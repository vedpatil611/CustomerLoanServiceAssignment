package com.barclays.loanmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.loanmanagement.constants.SystemConstant;
import com.barclays.loanmanagement.dto.CustomerDTO;
import com.barclays.loanmanagement.dto.LoanDTO;
import com.barclays.loanmanagement.entity.Customer;
import com.barclays.loanmanagement.entity.Loan;
import com.barclays.loanmanagement.exception.BarclaysBankException;
import com.barclays.loanmanagement.repository.CustomerRepository;
import com.barclays.loanmanagement.repository.LoanRepository;
import com.barclays.loanmanagement.utils.LoanStatus;

/**
 * CustomerLoanServiceImpl - CustomerLoanService implementation
 * @author Ved
 *
 */
@Service(value = "customerLoanService")
public class CustomerLoanServiceImpl implements CustomerLoanService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	LoanRepository loanRepository;

	@Override
	public LoanDTO getLoanDetails(Integer loanId) throws BarclaysBankException {
		Optional<Loan> opt = loanRepository.findById(loanId);
		if (!opt.isPresent())
			throw new BarclaysBankException(SystemConstant.LOAN_ID_NOT_FOUND_RESPONSE);
		
		Loan loan = opt.get();
		return new LoanDTO(loan, new CustomerDTO(loan.getCustomer()));
	}

	@Override
	public Integer addLoanAndCustomer(LoanDTO loanDTO) throws BarclaysBankException {
		Loan loan = loanDTO.toLoanEntity();
		
		if (loanDTO.getCustomer() == null)
			throw new BarclaysBankException(SystemConstant.CUSTOMER_DETAILS_NOT_PROVIDED_RESPONSE);
		
		Customer customer = loanDTO.getCustomer().toCustomerEntity();
		Customer newCustomer = customerRepository.save(customer);
		loan.setCustomer(newCustomer);
		Loan newLoan = loanRepository.save(loan);
		loanDTO.getCustomer().setCustomerId(newCustomer.getCustomerId());
		
		return newLoan.getLoanId();
	}

	@Override
	public Integer sanctionLoanToExistingCustomer(Integer customerId, LoanDTO loanDTO) throws BarclaysBankException {
		Optional<Customer> customerOpt = customerRepository.findById(customerId);
		if (!customerOpt.isPresent())
			throw new BarclaysBankException(SystemConstant.CUSTOMER_NOT_FOUND_RESPONSE);
		
		Loan loan = loanDTO.toLoanEntity();
		loan.setCustomer(customerOpt.get());
		Loan newLoan = loanRepository.save(loan);
		
		return newLoan.getLoanId();
	}

	@Override
	public void deleteLoan(Integer loanId) throws BarclaysBankException {
		Optional<Loan> opt = loanRepository.findById(loanId);
		if (!opt.isPresent())
			throw new BarclaysBankException(SystemConstant.LOAN_ID_NOT_FOUND_RESPONSE);
		
		loanRepository.delete(opt.get());
	}

	@Override
	public void closeLoan(Integer loanId) throws BarclaysBankException {
		Optional<Loan> opt = loanRepository.findById(loanId);
		if (!opt.isPresent())
			throw new BarclaysBankException(SystemConstant.LOAN_ID_NOT_FOUND_RESPONSE);
		
		Loan loan = opt.get();
		loan.setStatus(LoanStatus.Closed);
		loanRepository.save(loan);
	}
}
