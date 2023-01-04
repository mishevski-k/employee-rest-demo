package com.bithub.payroll.expections;

/**
 * RuntimeException for Employee Service
 *
 * @author Kiril Mishevski
 * @version 1.0.0
 * @since 1/3/2023
 */
public class EmployeeNotFoundException extends RuntimeException{

    public EmployeeNotFoundException(Long id){
        super("Could not find employee " + id);
    }
}
