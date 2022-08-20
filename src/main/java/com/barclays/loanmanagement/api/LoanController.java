package com.barclays.loanmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.loanmanagement.constants.ApiMapping;
import com.barclays.loanmanagement.constants.SystemConstant;
import com.barclays.loanmanagement.dto.LoanDTO;
import com.barclays.loanmanagement.exception.BarclaysBankException;
import com.barclays.loanmanagement.service.CustomerLoanService;

/**
 * LoanController - Loan api routes
 * @author Ved
 *
 */
@RestController
@RequestMapping(ApiMapping.LOAN_CONTROLLER_ROOT)
public class LoanController {
	
	@Autowired
	CustomerLoanService customerLoanService;
	
	@GetMapping(ApiMapping.GET_LOAN)
	public ResponseEntity<LoanDTO> getLoanDetail(@PathVariable Integer loanId) throws BarclaysBankException {
		LoanDTO loan = customerLoanService.getLoanDetails(loanId);
		return new ResponseEntity<>(loan, HttpStatus.OK);
	}
	
	@PostMapping(ApiMapping.ADD_LOAN_AND_CUSTOMER)
	public ResponseEntity<Integer> addLoanAndCustomer(@RequestBody LoanDTO loanDTO) throws BarclaysBankException {
		System.out.println(loanDTO);
		return new ResponseEntity<>(customerLoanService.addLoanAndCustomer(loanDTO), HttpStatus.OK);
	}
	
	@DeleteMapping(ApiMapping.DELETE_LOAN)
	public ResponseEntity<String> deleteLoan(@PathVariable Integer loanId) throws BarclaysBankException {
		customerLoanService.deleteLoan(loanId);
		return new ResponseEntity<>(SystemConstant.LOAN_DELETE_SUCCESS_RESPONSE, HttpStatus.OK);
	}
	
	@PutMapping(ApiMapping.CLOSE_LOAN)
	public ResponseEntity<String> closeLoan(@PathVariable Integer loanId) throws BarclaysBankException {
		customerLoanService.closeLoan(loanId);
		return new ResponseEntity<>(SystemConstant.LOAN_CLOSE_SUCCESS_RESPONSE, HttpStatus.OK);
	}

}
