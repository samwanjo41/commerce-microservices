package com.samwanjohi.orderservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "t_order_items")
@Entity
public class OrderItems {
    @Id
    @GeneratedValue
    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
