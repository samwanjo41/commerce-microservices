package com.samwanjohi.orderservice.service;

import com.samwanjohi.orderservice.dto.InventoryResponse;
import com.samwanjohi.orderservice.dto.OrderItemsDTO;
import com.samwanjohi.orderservice.dto.OrderRequest;
import com.samwanjohi.orderservice.model.Order;
import com.samwanjohi.orderservice.model.OrderItems;
import com.samwanjohi.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderItems> orderItems = orderRequest.getOrderItemsDTOList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderItemsList(orderItems);

        List<String> skuCodes = order.getOrderItemsList().stream()
                .map(OrderItems::getSkuCode)
                .toList();

        //TODO: Call inventory service and place order if product is in stock
        InventoryResponse[] inventoryResponsArray = webClient.get()
                .uri("http://localhost:8083/api/v1/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean allProductsInStock = Arrays.stream(inventoryResponsArray)
                .allMatch(InventoryResponse::isInStock);

        if(allProductsInStock){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product is not in stock. Try again later");
        }


    }

    private OrderItems mapToDto(OrderItemsDTO orderLineItemsDto) {
        OrderItems orderLineItems = new OrderItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }


}
