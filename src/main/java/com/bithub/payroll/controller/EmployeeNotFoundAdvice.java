package com.bithub.payroll.controller;

import com.bithub.payroll.expections.EmployeeNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * Rendering an response body when a HTTP 404 (NOT_FOUND) Exception is triggered
 *
 * @author Kiril Mishevski
 * @version 1.0.0
 * @since 1/3/2023
 */
@ControllerAdvice
public class EmployeeNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String employeeNotFoundHandler(EmployeeNotFoundException ex){
        return ex.getMessage();
    }
}
