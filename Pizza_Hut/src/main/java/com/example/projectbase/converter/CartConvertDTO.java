package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.CartCreateDTO;
import com.example.projectbase.domain.dto.response.CartResponseDTO;
import com.example.projectbase.domain.dto.response.ComboCartDTO;
import com.example.projectbase.domain.entity.CartEntity;
import com.example.projectbase.domain.entity.ComboDetailEntity;
import com.example.projectbase.repository.ComboDetailRepository;
import com.example.projectbase.repository.ProductDetailRepository;
import com.example.projectbase.service.ComboDetailService;
import com.example.projectbase.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class CartConvertDTO {
    @Autowired
    private UserService userService;
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ComboDetailRepository comboDetailRepository;
    @Autowired
    private ProductDetailConverter productDetailConverter;
    @Autowired
    private ComboDetailService comboDetailService;
    @Autowired
    private CartComboConverter cartComboConverter;
    public CartEntity convertDTOToEntity(CartCreateDTO cartCreateDTO){
        CartEntity cartEntity=userService.getCurrentUser().getCartEntity();
        cartEntity.getProductDetailEntities().add(productDetailRepository.findById(cartCreateDTO.getProductDetailId()).get());
        cartEntity.getComboDetailEntities().add(comboDetailRepository.findById(cartCreateDTO.getComboDetailId()).get());
        return  cartEntity;
    }
    public CartResponseDTO convertEntityToDTO(CartEntity cartEntity){
        CartResponseDTO cartResponseDTO=new CartResponseDTO();
        cartResponseDTO.setProductDetailResponseDTOS(productDetailConverter.convertListEntityToListDTO(cartEntity.getProductDetailEntities()));
        cartResponseDTO.setComboCartDTOS(cartComboConverter.convertListEntityToListDTO(cartEntity.getComboDetailEntities()));
        return cartResponseDTO;
    }
}
