package com.bithub.payroll.controller;

import com.bithub.payroll.domain.Employee;
import com.bithub.payroll.domain.EmployeeModelAssembler;
import com.bithub.payroll.expections.EmployeeNotFoundException;
import com.bithub.payroll.repository.EmployeeRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


/**
 * HTTP Controller for Employee Service
 * Basic CRUD Operations
 *
 * @author Kiril Mishevski
 * @version 1.0.0
 * @since 1/3/2023
 */
@RestController
public class EmployeeController {
    private final EmployeeRepository employeeRepository;
    private final EmployeeModelAssembler employeeModelAssembler;

    //Dependency Injection with repository for CRUD operations
    EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler employeeModelAssembler){
        this.employeeRepository = employeeRepository;
        this.employeeModelAssembler = employeeModelAssembler;
    }

    /**
     * Return all employees
     * @return List<Employee>
     */
    @GetMapping("/employees")
    public CollectionModel<EntityModel<Employee>> all(){
        List<EntityModel<Employee>> employees = employeeRepository.findAll().stream()
                .map(employeeModelAssembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(employees,
                linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
    }

    /**
     * Create new Employee
     *
     * @param newEmployee POST Body to create new entry
     * @return Employee the newly created Employee entry
     */
    @PostMapping("/employees")
    ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee){
        EntityModel<Employee> entityModel = employeeModelAssembler.toModel(employeeRepository.save(newEmployee));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /**
     * Get specific Employee by path variable(id)
     *
     * @param id Path variable to get specific Employee
     * @return Employee || EmployeeNotFoundException
     */
    @GetMapping("/employees/{id}")
    public EntityModel<Employee> one(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(()-> new EmployeeNotFoundException(id));

        return employeeModelAssembler.toModel(employee);
    }

    /**
     * Updating Employee entry, if not exists will create new
     *
     * @param newEmployee POST Body to update or create new Employee
     * @param id path variable to update new Employee, if not found will create new entry
     * @return Employee
     */
    @PutMapping("/employees/{id}")
    ResponseEntity<?> replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
        Employee updatedEmployee = employeeRepository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return employeeRepository.save(newEmployee);
                })
                .orElseGet(()->{
                   newEmployee.setId(id);
                   return employeeRepository.save(newEmployee);
                });

        EntityModel<Employee> entityModel = employeeModelAssembler.toModel(updatedEmployee);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    /**
     * Delete specific Employee
     *
     * @param id Path variable to delete specific Employee
     */
    @DeleteMapping("/employees/{id}")
    ResponseEntity<?> deleteEmployee(@PathVariable Long id){
        Employee foundEmployee = employeeRepository.findById(id)
                        .orElseThrow(()-> new EmployeeNotFoundException(id));

        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
