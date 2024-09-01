package com.codedecode.order.service;

import com.codedecode.order.dto.OrderDTO;
import com.codedecode.order.dto.OrderDTOFromFE;
import com.codedecode.order.dto.UserDTO;
import com.codedecode.order.entity.Order;
import com.codedecode.order.mapper.OrderMapper;
import com.codedecode.order.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    SequenceGenerator sequenceGenerator;

    public OrderDTO saveOrder(OrderDTOFromFE orderDetails) {
        Integer newOrderId = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchUserInfoByUserId(orderDetails.getUserId());
        Order order = new Order(newOrderId, orderDetails.getFoodItemsList(), orderDetails.getRestaurant(), userDTO);
        Order savedOrder = orderRepo.save(order);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(order);
    }

    private UserDTO fetchUserInfoByUserId(Integer userId) {
        return restTemplate.getForObject("http://USER-INFO-SERVICE/user/fetchById/" + userId, UserDTO.class);
    }
}
