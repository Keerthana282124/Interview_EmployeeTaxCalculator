package com.interview.code.taxcalculator.EmployeeTaxCalculator.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.interview.code.taxcalculator.EmployeeTaxCalculator.model.EmployeeTax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.interview.code.taxcalculator.EmployeeTaxCalculator.model.EmployeeDetails;
import com.interview.code.taxcalculator.EmployeeTaxCalculator.repository.EmployeeRepository;


@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;



    public List<EmployeeTax> getAllEmployeesTax() {
        List<EmployeeTax> employeeTaxes = new ArrayList<>();

        employeeRepository.findAll().forEach(employee -> {
            EmployeeTax employeeTax = new EmployeeTax();
            Double totalSalary = 0.0;
            Double taxSalary = 0.0;
            double totalTax = 0.0;
            double temp = 0.0;
            double montlySalary = 0.0;
            Calendar c = Calendar.getInstance();
            c.set(Calendar.MONTH, 04);

            // set Date
            c.set(Calendar.DATE, 01);

            // set Year
            c.set(Calendar.YEAR, 2020);

            Date dateOne = c.getTime();

            taxSalary = employee.getSalary();

            if ((taxSalary > 0) && (taxSalary > 250000) && (taxSalary <= 500000)) {
                totalTax += taxSalary * 0.05;
            }

            if ((taxSalary > 0) && (taxSalary > 500000) && (taxSalary <= 1000000)) {
                totalTax += taxSalary * 0.1;
            }

            if ((taxSalary > 0) && (taxSalary > 1000000)) {
                totalTax += taxSalary * 0.2;
            }

            employeeTax.setYearlySalary((employee.getSalary() * 12) - totalTax);

            employeeTax.setTaxAmount(totalTax);
            employeeTax.setEmployeeCode(employee.getEmployeeId());
            employeeTax.setFirstName(employee.getFirstName());
            employeeTax.setLastName(employee.getLastName());
            employeeTax.setCessAmount(0.0);

            //calculate
            totalSalary = employee.getSalary();
            montlySalary = totalSalary / 12;
            if (taxSalary > 2500000) {
                employeeTax.setCessAmount((300000) * 0.02);
            }
            if (employee.getDoj().before(dateOne)) {
                int da = employee.getDoj().getDate();
                int mo = employee.getDoj().getMonth();
                for (int j = mo + 1; j <= 12; j++) {
                    temp += montlySalary;
                }
                for (int j = mo; j <= 3; j++) {
                    temp += montlySalary;
                }
                temp += montlySalary - da;

                employeeTax.setYearlySalary(temp);
            }

            employeeTaxes.add(employeeTax);
        });

        return employeeTaxes;
    }


    //getting a specific record
    public EmployeeDetails getEmployeeById(int id) {
        return employeeRepository.findById(id).get();
    }

    public void saveOrUpdate(EmployeeDetails employee) {
        employeeRepository.save(employee);

    }

    //getting all employee records
    public List<EmployeeDetails> getAllEmployees() {
        List<EmployeeDetails> employees = new ArrayList<EmployeeDetails>();
        employeeRepository.findAll().forEach(employee -> employees.add(employee));
        return employees;
    }

}
