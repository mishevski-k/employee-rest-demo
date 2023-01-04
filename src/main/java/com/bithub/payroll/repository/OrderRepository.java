package com.bithub.payroll.repository;

import com.bithub.payroll.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
