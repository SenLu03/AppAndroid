package com.example.projectbase.service.impl;

import com.example.projectbase.converter.ProductDetailConverter;
import com.example.projectbase.domain.dto.request.ProductDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ProductDetailResponseDTO;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import com.example.projectbase.repository.ProductDetailRepository;
import com.example.projectbase.service.ProductDetailService;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;
    @Autowired
    private ProductDetailConverter productDetailConverter;
    @Autowired
    private UserService userService;
    @Override
    public ProductDetailResponseDTO create(ProductDetailCreateDTO productDetailCreateDTO) {
        ProductDetailEntity productDetailEntity=productDetailConverter.convertDTOToEntity(productDetailCreateDTO);
        return productDetailConverter.convertEntityToDTO(productDetailRepository.save(productDetailEntity));
    }

    @Override
    public ProductDetailResponseDTO addToCart(Long id) {
        ProductDetailEntity productDetailEntity=productDetailRepository.findById(id).get();
        ProductDetailEntity productDetailEntitySave =productDetailRepository.findProductDetailEntitiesByProductEntityAndSizeEntityAndCakeBaseEntityAndCartEntity(productDetailEntity.getProductEntity(),productDetailEntity.getSizeEntity(),productDetailEntity.getCakeBaseEntity(),userService.getCurrentUser().getCartEntity()).orElse(productDetailEntity);
        if(productDetailEntitySave.getId()!=id){
            deleteById(id);
        }
        productDetailEntitySave.setQuatity(productDetailEntitySave.getQuatity()+1);
        productDetailEntity.setCartEntity(userService.getCurrentUser().getCartEntity());
        return productDetailConverter.convertEntityToDTO(productDetailRepository.save(productDetailEntitySave));

    }
    @Override
    public void deleteById(Long id) {
        if(productDetailRepository.findById(id).isPresent()){
            productDetailRepository.deleteById(id);
        }
        else{
            throw  new NullPointerException("Khong tim thay id");
        }
    }


}
