package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.response.CakeBaseResponseDTO;
import com.example.projectbase.domain.entity.CakeBaseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CakeBaseConverter {

    public CakeBaseResponseDTO converEntityToDTO(CakeBaseEntity cakeBaseEntity){
        CakeBaseResponseDTO cakeBaseResponseDTO=new CakeBaseResponseDTO();
        cakeBaseResponseDTO.setName(cakeBaseEntity.getName());
        cakeBaseResponseDTO.setId(cakeBaseEntity.getId());
        return cakeBaseResponseDTO;
    }
    public List<CakeBaseResponseDTO> converListEntityToListDTO(List<CakeBaseEntity> list){
        List<CakeBaseResponseDTO> list1=new ArrayList<>();
        for(CakeBaseEntity x:list){
            list1.add(converEntityToDTO(x));
        }
        return list1;
    }
}
