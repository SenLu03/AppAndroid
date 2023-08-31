package com.example.projectbase.converter;

import com.example.projectbase.domain.dto.response.SizeResponseDTO;
import com.example.projectbase.domain.entity.SizeEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class SizeConverter {
    public SizeResponseDTO converEntityToDTO(SizeEntity sizeEntity){
        SizeResponseDTO sizeResponseDTO=new SizeResponseDTO();
        sizeResponseDTO.setId(sizeEntity.getId());
        sizeResponseDTO.setName(sizeEntity.getName());
        return sizeResponseDTO;
    }
    public List<SizeResponseDTO> converListEntityToListDTO(List<SizeEntity> list){
        List<SizeResponseDTO> list1=new ArrayList<>();
        for(SizeEntity x:list){
            list1.add(converEntityToDTO(x));
        }
        return list1;
    }
}
