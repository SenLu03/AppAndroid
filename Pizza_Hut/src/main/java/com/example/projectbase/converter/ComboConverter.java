package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.ComboCreateDTO;
import com.example.projectbase.domain.dto.response.ComboResponseDTO;
import com.example.projectbase.domain.entity.CategoryEntity;
import com.example.projectbase.domain.entity.ComboEntity;
import com.example.projectbase.repository.CategoryRepository;
import com.example.projectbase.service.CategoryService;
import com.example.projectbase.util.UploadFileUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ComboConverter {
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    UploadFileUtil uploadFileCloudinary;
    @Autowired
    CategoryRepository categoryRepository;

    public ComboEntity converDTOToEntity(ComboCreateDTO comboCreateDTO){
        ComboEntity comboEntity=modelMapper.map(comboCreateDTO,ComboEntity.class);
        try {
            String urlImage = uploadFileCloudinary.uploadFile(comboCreateDTO.getImage());
            comboEntity.setImage(urlImage);
        }catch (Exception e) {
            e.printStackTrace();
        }
        CategoryEntity categoryEntity=categoryRepository.findById(comboCreateDTO.getCategoryId()).get();
        comboEntity.setCategoryEntity(categoryEntity);
        return comboEntity;
    }
    public ComboResponseDTO convertEntityToDTO(ComboEntity comboEntity){
        ComboResponseDTO comboResponseDTO=modelMapper.map(comboEntity,ComboResponseDTO.class);
        comboResponseDTO.setCategoryId(comboEntity.getCategoryEntity().getId());
        return comboResponseDTO;
    }
    public List<ComboResponseDTO> converListEntityToListDTO(List<ComboEntity> comboEntityList){
        List<ComboResponseDTO> comboResponseDTOS=new ArrayList<>();
        for(ComboEntity comboEntity: comboEntityList){
            comboResponseDTOS.add(convertEntityToDTO(comboEntity));
        }
        return comboResponseDTOS;
    }
}
