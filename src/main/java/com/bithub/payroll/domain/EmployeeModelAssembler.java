package com.bithub.payroll.domain;

import com.bithub.payroll.controller.EmployeeController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Converting Employee Model with only contained data to EntityModel of Employee with links to corresponding relationships
 *
 * @author Kiril Mishevski
 * @version 1.0.0
 * @since 1/3/2023
 */
@Component
public class EmployeeModelAssembler implements RepresentationModelAssembler<Employee, EntityModel<Employee>> {
    @Override
    public EntityModel<Employee> toModel(Employee employee){
        return EntityModel.of(employee,
                linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
    }
}
