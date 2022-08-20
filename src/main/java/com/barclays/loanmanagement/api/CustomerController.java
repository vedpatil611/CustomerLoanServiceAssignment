package com.barclays.loanmanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.loanmanagement.constants.ApiMapping;
import com.barclays.loanmanagement.dto.LoanDTO;
import com.barclays.loanmanagement.exception.BarclaysBankException;
import com.barclays.loanmanagement.service.CustomerLoanService;

/**
 * CustomerController - customer api routes
 * @author Ved
 *
 */
@RestController
@RequestMapping(ApiMapping.CUSTOMER_CONTROLLER_ROOT)
public class CustomerController {
	
	@Autowired
	CustomerLoanService customerLoanService;
	
	@PostMapping(ApiMapping.SANCATION_NEW_LOAN)
	public ResponseEntity<Integer> sanctionLoanToExistingCustomer(@PathVariable Integer customerId, @RequestBody LoanDTO loanDTO) throws BarclaysBankException {
		return new ResponseEntity<>(customerLoanService.sanctionLoanToExistingCustomer(customerId, loanDTO), HttpStatus.OK);
	}
}
