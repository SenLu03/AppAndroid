package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.CategoryCreateDTO;
import com.example.projectbase.domain.dto.response.CategoryResponseDTO;
import com.example.projectbase.domain.entity.CategoryEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryConverter {
    @Autowired
    ModelMapper modelMapper;

    public CategoryEntity converDTOToEntity(CategoryCreateDTO categoryDTO){
        CategoryEntity categoryEntity = modelMapper.map(categoryDTO, CategoryEntity.class);
        return categoryEntity;
    }

    public CategoryCreateDTO converEntityToDTO(CategoryEntity categoryEntity){
        CategoryCreateDTO categoryDTO = modelMapper.map(categoryEntity, CategoryCreateDTO.class);
        return categoryDTO;
    }

    public Object converListEntityToListDTO(List<CategoryEntity> categoryEntities) {
        List<CategoryCreateDTO> categoryDTOS = new ArrayList<>();
        for (CategoryEntity item : categoryEntities){
            CategoryCreateDTO categoryDTO = modelMapper.map(item, CategoryCreateDTO.class);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }

    public List<CategoryResponseDTO> converListEntityToListDTOResponse(List<CategoryEntity> categoryEntities) {
        List<CategoryResponseDTO> categoryDTOS = new ArrayList<>();
        for (CategoryEntity item : categoryEntities){
            CategoryResponseDTO categoryDTO = modelMapper.map(item, CategoryResponseDTO.class);
            categoryDTOS.add(categoryDTO);
        }
        return categoryDTOS;
    }
}
