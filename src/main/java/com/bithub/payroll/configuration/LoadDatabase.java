package com.bithub.payroll.configuration;

import com.bithub.payroll.domain.Employee;
import com.bithub.payroll.domain.Order;
import com.bithub.payroll.domain.Status;
import com.bithub.payroll.repository.EmployeeRepository;
import com.bithub.payroll.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Loading database on project start
 *
 * @author Kiril Mishevski
 * @version 1.0.0
 * @since 1/3/2023
 */
@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    //CommandLineRunner once the application context is loaded, will create and store two employee Entities based on the Employee Domain and with the help of the EmployeeRepository
    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository, OrderRepository orderRepository){
        return args -> {
            employeeRepository.save(new Employee("Bilbo", "Baggins", "burglar"));
            employeeRepository.save(new Employee("Frodo", "Baggins", "thief"));

            employeeRepository.findAll().forEach(employee -> {
                log.info("Preloaded " + employee);
            });

            orderRepository.save(new Order("Macbook Pro", Status.COMPLETED));
            orderRepository.save(new Order("Iphone X", Status.IN_PROGRESS));

            orderRepository.findAll().forEach(order -> {
                log.info("Preloaded " + order);
            });

        };
    }
}
