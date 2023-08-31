package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.CartCreateDTO;
import com.example.projectbase.domain.dto.response.CartResponseDTO;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import com.example.projectbase.domain.entity.ProductEntity;

import java.util.List;

public interface CartService {
    CartResponseDTO findCart();
    Long getTotalMoney();
}
