package com.interview.code.taxcalculator.EmployeeTaxCalculator.controller;


import java.util.List;

import com.interview.code.taxcalculator.EmployeeTaxCalculator.model.EmployeeStatus;
import com.interview.code.taxcalculator.EmployeeTaxCalculator.model.EmployeeTax;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.interview.code.taxcalculator.EmployeeTaxCalculator.model.EmployeeDetails;
import com.interview.code.taxcalculator.EmployeeTaxCalculator.service.EmployeeService;


//creating RestController
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    //get mapping that retrieves all the employee tax details
    @GetMapping("/employeesTax")
    private List<EmployeeTax> getAllEmployees() {
        return employeeService.getAllEmployeesTax();
    }

    //get mapping that retrieves the details of a specific employee
    @GetMapping("/employee/{id}")
    private EmployeeDetails getEmployee(@PathVariable("id") int id) {
        return employeeService.getEmployeeById(id);
    }

    //post mapping that save the employee details in the database
    @PostMapping("/employeeStatus")
    private EmployeeStatus saveEmployee(@RequestBody EmployeeDetails employee) {
        EmployeeStatus employeeStatus = new EmployeeStatus();

        if (validateInput(employee)) {
            employeeService.saveOrUpdate(employee);
            employeeStatus.setStatusCode(200);
            employeeStatus.setStatusMessage("Inserted Successfully");
        } else {
            employeeStatus.setStatusMessage("Please check your inputs");
            employeeStatus.setStatusCode(500);
        }
        return employeeStatus;
    }

    private boolean validateInput(EmployeeDetails employee){
        return StringUtils.isNotBlank(employee.getLastName()) && StringUtils.isNotBlank(employee.getEmail()) && StringUtils.isNotBlank(employee.getFirstName());
        //return true;
    }
}