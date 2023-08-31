package com.example.projectbase.service.impl;

import com.example.projectbase.converter.OrderConverter;
import com.example.projectbase.domain.dto.request.OrderCreateDTO;
import com.example.projectbase.domain.dto.response.OrderResponseDTO;
import com.example.projectbase.domain.entity.CartEntity;
import com.example.projectbase.domain.entity.OrderEntity;
import com.example.projectbase.domain.entity.UserEntity;
import com.example.projectbase.repository.CartRepository;
import com.example.projectbase.repository.OrderRepository;
import com.example.projectbase.repository.UserRepository;
import com.example.projectbase.service.OrderService;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderConverter orderConverter;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Override
    public OrderResponseDTO create(OrderCreateDTO orderCreateDTO) {
        OrderEntity orderEntity=orderConverter.convertDTOToEntity(orderCreateDTO);
        CartEntity cartEntity=new CartEntity();
        cartRepository.save(cartEntity);
        UserEntity user=userService.getCurrentUser();
        user.setPoint(user.getPoint()+ orderEntity.getTotal_money()/10000);
        user.setCartEntity(cartEntity);
        userRepository.save(user);
        return orderConverter.convertEntityToDTO(orderRepository.save(orderEntity));
    }

    @Override
    public List<OrderResponseDTO> findAll() {
        return orderConverter.convertListEntityToDTO(orderRepository.findAllByUserEntity(userService.getCurrentUser()));
    }
}
