package com.bithub.payroll.repository;

import com.bithub.payroll.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data Jpa repository with methods that help with creating, updating, reading and deleting records
 *
 * @author Kiril Mishevski
 * @version 1.0.0
 * @since 1/3/2023
 */
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
