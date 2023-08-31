package com.example.projectbase.service.impl;

import com.example.projectbase.converter.CartConvertDTO;
import com.example.projectbase.domain.dto.response.CartResponseDTO;
import com.example.projectbase.domain.entity.CartEntity;
import com.example.projectbase.domain.entity.ComboDetailEntity;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import com.example.projectbase.repository.CartRepository;
import com.example.projectbase.service.CartService;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CartConvertDTO cartConvertDTO;
    @Autowired UserService userService;

    @Override
    public CartResponseDTO findCart() {
        return cartConvertDTO.convertEntityToDTO(userService.getCurrentUser().getCartEntity());
    }

    @Override
    public Long getTotalMoney() {
      Long totalMoney=0L;
        CartEntity cartEntity=userService.getCurrentUser().getCartEntity();
        List<ProductDetailEntity> productDetailEntities=cartEntity.getProductDetailEntities();
        for(ProductDetailEntity x: productDetailEntities){
            totalMoney+=(x.getQuatity()*x.getPrice());
        }
        List<ComboDetailEntity> comboDetailEntities=cartEntity.getComboDetailEntities();
        for(ComboDetailEntity x:comboDetailEntities){
            totalMoney+=(x.getPrice());
        }
        return totalMoney;
    }
}
