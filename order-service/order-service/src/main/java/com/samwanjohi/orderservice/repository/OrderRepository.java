package com.samwanjohi.orderservice.repository;

import com.samwanjohi.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
