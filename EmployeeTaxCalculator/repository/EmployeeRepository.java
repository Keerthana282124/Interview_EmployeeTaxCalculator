package com.interview.code.taxcalculator.EmployeeTaxCalculator.repository;


import org.springframework.data.repository.CrudRepository;
import com.interview.code.taxcalculator.EmployeeTaxCalculator.model.EmployeeDetails;


public interface EmployeeRepository extends CrudRepository<EmployeeDetails, Integer>
{
}