package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.ProductDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ProductDetailResponseDTO;
import com.example.projectbase.domain.entity.ProductDetailEntity;
import com.example.projectbase.domain.entity.ProductEntity;
import com.example.projectbase.repository.CakeBaseRepository;
import com.example.projectbase.repository.ProductRepository;
import com.example.projectbase.repository.SizeRepository;
import com.example.projectbase.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class ProductDetailConverter {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    CakeBaseRepository cakeBaseRepository;
    @Autowired
    UserService userService;
    public ProductDetailEntity convertDTOToEntity(ProductDetailCreateDTO productDetailCreateDTO){
        ProductDetailEntity productDetailEntity=new ProductDetailEntity();
        productDetailEntity.setProductEntity(productRepository.findById(productDetailCreateDTO.getProductId()).orElseThrow(() -> new NullPointerException("khong tim thay product")));
        if(productDetailCreateDTO.getSizeId()!=null){
            productDetailEntity.setSizeEntity(sizeRepository.findById(productDetailCreateDTO.getSizeId()).get());
        }
        if(productDetailCreateDTO.getCakeBaseId()!=null){
            productDetailEntity.setCakeBaseEntity(cakeBaseRepository.findById(productDetailCreateDTO.getCakeBaseId()).get());
        }
        productDetailEntity.setPrice(productDetailEntity.getProductEntity().getPrice()+productDetailEntity.getSizeEntity().getPrice());

        return productDetailEntity;
    }
    public ProductDetailResponseDTO convertEntityToDTO(ProductDetailEntity productDetailEntity){
        ProductDetailResponseDTO productDetailResponseDTO=new ProductDetailResponseDTO();
        productDetailResponseDTO.setPrice(productDetailEntity.getPrice());
        productDetailResponseDTO.setQuatity(productDetailEntity.getQuatity());
        productDetailResponseDTO.setId(productDetailEntity.getId());
        productDetailResponseDTO.setProductName(productDetailEntity.getProductEntity().getName());
        if(productDetailEntity.getSizeEntity()!=null){
            productDetailResponseDTO.setSizeName(productDetailEntity.getSizeEntity().getName());
        }
        if(productDetailEntity.getCakeBaseEntity()!=null){
            productDetailResponseDTO.setCakeBaseName(productDetailEntity.getCakeBaseEntity().getName());
        }
        return productDetailResponseDTO;
    }
    public List<ProductDetailResponseDTO> convertListEntityToListDTO(List<ProductDetailEntity> productDetailEntities){
        List<ProductDetailResponseDTO> productDetailResponseDTOS=new ArrayList<>();
        for(ProductDetailEntity x:productDetailEntities){
            productDetailResponseDTOS.add(convertEntityToDTO(x));
        }
        return productDetailResponseDTOS;
    }
}
