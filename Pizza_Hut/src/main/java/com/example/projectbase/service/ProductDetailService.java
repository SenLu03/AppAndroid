package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.ProductDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ProductDetailResponseDTO;
import com.example.projectbase.domain.entity.ProductDetailEntity;

import java.util.List;

public interface ProductDetailService {
    public ProductDetailResponseDTO create(ProductDetailCreateDTO productDetailCreateDTO);
    ProductDetailResponseDTO addToCart(Long id);
    void deleteById(Long id);
}
