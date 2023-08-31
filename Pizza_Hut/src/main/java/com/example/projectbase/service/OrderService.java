package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.OrderCreateDTO;
import com.example.projectbase.domain.dto.response.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    public OrderResponseDTO create(OrderCreateDTO orderCreateDTO);
    public List<OrderResponseDTO> findAll();
}
