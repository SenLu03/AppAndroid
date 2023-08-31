package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.OrderCreateDTO;
import com.example.projectbase.domain.dto.response.OrderResponseDTO;
import com.example.projectbase.domain.entity.OrderEntity;
import com.example.projectbase.service.CartService;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderConverter {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CartConvertDTO cartConvertDTO;
    public OrderEntity convertDTOToEntity(OrderCreateDTO orderCreateDTO){
        OrderEntity orderEntity=new OrderEntity();
        orderEntity.setUserEntity(userService.getCurrentUser());
        orderEntity.setCartEntity(userService.getCurrentUser().getCartEntity());
        orderEntity.setDelivery_address(orderCreateDTO.getDelivery_address());
        orderEntity.setStatus("đã đặt");
        orderEntity.setTotal_money(cartService.getTotalMoney());
        return orderEntity;
    }
    public OrderResponseDTO convertEntityToDTO(OrderEntity orderEntity){
        OrderResponseDTO orderResponseDTO=new OrderResponseDTO();
        orderResponseDTO.setTotalMoney(orderEntity.getTotal_money());
        orderResponseDTO.setCartResponseDTO(cartConvertDTO.convertEntityToDTO(orderEntity.getCartEntity()));
        return orderResponseDTO;
    }
    public List<OrderResponseDTO> convertListEntityToDTO(List<OrderEntity> orderEntities){
        List<OrderResponseDTO> orderResponseDTOS=new ArrayList<>();
        for(OrderEntity x:orderEntities){
            orderResponseDTOS.add(convertEntityToDTO(x));
        }
        return orderResponseDTOS;
    }
}
