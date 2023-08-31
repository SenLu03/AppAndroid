package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.ProductCreateDTO;
import com.example.projectbase.domain.dto.response.ProductResponseDTO;
import com.example.projectbase.domain.entity.CategoryEntity;
import com.example.projectbase.domain.entity.ProductEntity;
import com.example.projectbase.util.UploadFileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductConverter {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UploadFileUtil uploadFileCloudinary;

    public ProductEntity converDTOToEntity(ProductCreateDTO productDTO){
        ProductEntity productEntity = modelMapper.map(productDTO, ProductEntity.class);
        try {
            String urlImage = uploadFileCloudinary.uploadFile(productDTO.getImage());
            productEntity.setImage(urlImage);

        }catch (Exception e) {
            e.printStackTrace();
        }
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setId(productDTO.getCategoryId());
        productEntity.setCategoryEntity(categoryEntity);
        return productEntity;
    }

    public ProductResponseDTO converEntityToDTO(ProductEntity productEntity){
        ProductResponseDTO productResponseDTO = modelMapper.map(productEntity, ProductResponseDTO.class);
        productResponseDTO.setCategoryId(productEntity.getCategoryEntity().getId());
        productResponseDTO.setCakeBase("Đế Giòn Xốp");
        productResponseDTO.setCakeSize("nhỏ");
        return productResponseDTO;
    }

    public List<ProductEntity> converListDTOToListEntity(List<ProductCreateDTO> productDTOS){
        List<ProductEntity> productEntities = new ArrayList<>();
        for (ProductCreateDTO item : productDTOS){
            ProductEntity productEntity = modelMapper.map(item, ProductEntity.class);
            productEntities.add(productEntity);
        }
        return productEntities;
    }

    public List<ProductResponseDTO> converListEntityToListDTO(List<ProductEntity> productEntities){
        List<ProductResponseDTO> productResponseDTOS = new ArrayList<>();
        for (ProductEntity item : productEntities){
            ProductResponseDTO productResponseDTO = modelMapper.map(item, ProductResponseDTO.class);
            productResponseDTOS.add(productResponseDTO);
        }
        return productResponseDTOS;
    }
}
