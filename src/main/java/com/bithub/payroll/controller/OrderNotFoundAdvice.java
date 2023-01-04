package com.bithub.payroll.controller;

import com.bithub.payroll.expections.OrderNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ControllerAdvice
public class OrderNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String orderNotFoundHandler(OrderNotFoundException ex){
        return ex.getMessage();
    }
}
