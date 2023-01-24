package com.samwanjohi.orderservice.service;

import com.samwanjohi.orderservice.dto.OrderItemsDTO;
import com.samwanjohi.orderservice.dto.OrderRequest;
import com.samwanjohi.orderservice.model.Order;
import com.samwanjohi.orderservice.model.OrderItems;
import com.samwanjohi.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderItemsDTO> oditems = orderRequest.getOrderItemsDTOList();

        List<OrderItems> orderItems = oditems
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        order.setOrderItemsList(orderItems);

        orderRepository.save(order);

    }

    private OrderItems mapToDto(OrderItemsDTO orderItemsDTO) {
        OrderItems orderItems = new OrderItems();
        orderItems.setPrice(orderItemsDTO.getPrice());
        orderItems.setQuantity(orderItems.getQuantity());
        orderItems.setSkuCode(orderItems.getSkuCode());
        return orderItems;
    }
}
