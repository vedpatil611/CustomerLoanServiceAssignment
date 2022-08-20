package com.barclays.loanmanagement.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.loanmanagement.entity.Customer;

/**
 * CustomerRepository - Query on Customer table in database
 * @author Ved
 *
 */
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
