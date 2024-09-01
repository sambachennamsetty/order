package com.codedecode.order.dto;

import com.codedecode.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTOFromFE {

    private List<FoodItemDTO> foodItemsList;
    private Integer userId;
    private RestaurantDTO restaurant;

}
