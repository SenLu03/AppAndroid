package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.request.RoleRequestDTO;
import com.example.projectbase.domain.entity.RoleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {

    @Autowired
    ModelMapper modelMapper;

    public RoleEntity converDTOToEntity(RoleRequestDTO roleDTO){
        RoleEntity roleEntity = modelMapper.map(roleDTO, RoleEntity.class);
        return roleEntity;
    }

    public RoleRequestDTO converEntityToDTO(RoleEntity roleEntity){
        RoleRequestDTO roleDTO = modelMapper.map(roleEntity, RoleRequestDTO.class);
        return roleDTO;
    }
}
