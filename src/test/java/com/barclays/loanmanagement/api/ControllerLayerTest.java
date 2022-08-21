package com.barclays.loanmanagement.api;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.barclays.loanmanagement.constants.SystemConstant;
import com.barclays.loanmanagement.dto.CustomerDTO;
import com.barclays.loanmanagement.dto.LoanDTO;
import com.barclays.loanmanagement.exception.BarclaysBankException;
import com.barclays.loanmanagement.repository.CustomerRepository;
import com.barclays.loanmanagement.utils.LoanStatus;

/**
 * ControllerLayerTest - JUnits for controller layer
 * @author Ved
 *
 */
@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ControllerLayerTest {
	
	@Autowired
	CustomerController customerController;
	
	@Autowired
	LoanController loanController;
	
	@Autowired
	CustomerRepository customerRepository;
	
	static LoanDTO loanDTO1, loanDTO2;
	static CustomerDTO customerDTO1;
	
	/**
	 * initStatics - Initialize variables before test start
	 */
	@BeforeAll
	public static void initStatics() {
		loanDTO1 = new LoanDTO();
		loanDTO1.setAmount(123546.0);
		loanDTO1.setDate(LocalDate.now());
		loanDTO1.setStatus(LoanStatus.Open);
		
		loanDTO2 = new LoanDTO();
		loanDTO2.setAmount(123546.0);
		loanDTO2.setDate(LocalDate.now());
		loanDTO2.setStatus(LoanStatus.Open);
		
		customerDTO1 = new CustomerDTO();
		customerDTO1.setCustomerId(9999999);
		customerDTO1.setDateOfBirth(LocalDate.of(2000, 01, 01));
		customerDTO1.setEmailid("ved@gmail.com");
		customerDTO1.setName("ved");
	}
	
	/**
	 * addLoanAndCustomerFailureTest - Add new loan and customer but fail because customer data is not supplied
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(1)
	public void addLoanAndCustomerFailureTest1() throws BarclaysBankException {
		Assertions.assertThrows(BarclaysBankException.class, () -> loanController.addLoanAndCustomer(loanDTO1));
	}
	
	/**
	 * addLoanAndCustomerFailureTest - Add new loan and customer but fail because loan data is not given
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(1)
	public void addLoanAndCustomerFailureTest2() throws BarclaysBankException {
		Assertions.assertThrows(BarclaysBankException.class, () -> loanController.addLoanAndCustomer(null));
	}
	
	/**
	 * addLoanAndCustomerSuccessTest - Add new loan and customer
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(1)
	public void addLoanAndCustomerSuccessTest() throws BarclaysBankException {
		loanDTO1.setCustomer(customerDTO1);
		ResponseEntity<Integer> res = loanController.addLoanAndCustomer(loanDTO1);
		Assertions.assertEquals(res.getStatusCode(), HttpStatus.OK);
		loanDTO1.setLoanId(res.getBody());
	}
	
	/**
	 * getLoanInfoFailureTest - Get loan info but fail because loan id does not exist
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(2)
	public void getLoanInfoFailureTest() throws BarclaysBankException {
		Assertions.assertThrows(BarclaysBankException.class, () -> loanController.getLoanDetail(Integer.MAX_VALUE));
	}
	
	/**
	 * getLoanInfoSuccessTest - Get loan info from id
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(2)
	public void getLoanInfoSuccessTest() throws BarclaysBankException {
		ResponseEntity<LoanDTO> res = loanController.getLoanDetail(loanDTO1.getLoanId());
		Assertions.assertEquals(res.getBody().getLoanId(), loanDTO1.getLoanId());
	}
	
	/**
	 * closeLoanFailTest - Close loan test fail because loan id does not exist
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(3)
	public void closeLoanFailTest() throws BarclaysBankException {
		Assertions.assertThrows(BarclaysBankException.class, () -> loanController.closeLoan(Integer.MAX_VALUE));
	}
	
	/**
	 * closeLoanSuccessTest - Close loan successfully 
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(3)
	public void closeLoanSuccessTest() throws BarclaysBankException {
		ResponseEntity<String> res = loanController.closeLoan(loanDTO1.getLoanId());
		Assertions.assertEquals(res.getBody(), SystemConstant.LOAN_CLOSE_SUCCESS_RESPONSE);
	}
	
	/**
	 * addLoanToExistingCustomerFailTest - Add loan to existing customer but fail because invalid customer id is supplied
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(4)
	public void addLoanToExistingCustomerFailTest1() throws BarclaysBankException {
		Assertions.assertThrows(BarclaysBankException.class, () -> customerController.sanctionLoanToExistingCustomer(Integer.MAX_VALUE, loanDTO2));
	}
	
	/**
	 * addLoanToExistingCustomerFailTest - Add loan to existing customer but fail because loan details is not supplied
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(4)
	public void addLoanToExistingCustomerFailTest2() throws BarclaysBankException {
		Assertions.assertThrows(BarclaysBankException.class, () -> customerController.sanctionLoanToExistingCustomer(customerDTO1.getCustomerId(), null));
	}
	
	/**
	 * addLoanToExistingCustomerSuccessTest - Add loan to existing customer successfully
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(4)
	public void addLoanToExistingCustomerSuccessTest() throws BarclaysBankException {
		ResponseEntity<Integer> res = customerController.sanctionLoanToExistingCustomer(customerDTO1.getCustomerId(), loanDTO2);
		Assertions.assertEquals(res.getStatusCode(), HttpStatus.OK);
		loanDTO2.setLoanId(res.getBody());
	}
	
	/**
	 * deleteLoanFailTest - Delete loan fail because loan id does not exist
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(5)
	public void deleteLoanFailTest() throws BarclaysBankException {
		Assertions.assertThrows(BarclaysBankException.class, () -> loanController.deleteLoan(Integer.MAX_VALUE));
	}
	
	/**
	 * deleteLoanSuccessTest - Delete loan from database
	 * @throws BarclaysBankException
	 */
	@Test
	@Order(5)
	public void deleteLoanSuccessTest() throws BarclaysBankException {
		ResponseEntity<String> res = loanController.deleteLoan(loanDTO1.getLoanId());
		Assertions.assertEquals(res.getBody(), SystemConstant.LOAN_DELETE_SUCCESS_RESPONSE);
	
		res = loanController.deleteLoan(loanDTO2.getLoanId());
		Assertions.assertEquals(res.getBody(), SystemConstant.LOAN_DELETE_SUCCESS_RESPONSE);
		
		customerRepository.deleteById(customerDTO1.getCustomerId());
	}

	/**
	 * freeResource - Free static resources after all test
	 */
	@AfterAll
	public static void freeResource() {
		loanDTO1 = null;
		loanDTO2 = null;
		customerDTO1 = null;
	}
	
}
