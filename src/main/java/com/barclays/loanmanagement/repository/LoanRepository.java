package com.barclays.loanmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.loanmanagement.entity.Loan;

/**
 * LoanRepository - Query on Loan table in database
 * @author Ved
 *
 */
public interface LoanRepository extends CrudRepository<Loan, Integer> {

}
