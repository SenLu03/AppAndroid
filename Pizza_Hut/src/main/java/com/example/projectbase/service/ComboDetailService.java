package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.ComboDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ComboCartDTO;
import com.example.projectbase.domain.dto.response.ComboDetailResposeDTO;
import com.example.projectbase.domain.entity.ComboDetailEntity;

public interface ComboDetailService {
     ComboDetailResposeDTO create(ComboDetailCreateDTO comboDetailCreateDTO);
     ComboCartDTO findByComboId(Long comboId);
     ComboCartDTO addToCart(Long id);
     void deleteById(Long id);
}
