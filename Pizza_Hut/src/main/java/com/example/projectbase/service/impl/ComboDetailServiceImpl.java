package com.example.projectbase.service.impl;

import com.example.projectbase.converter.CartComboConverter;
import com.example.projectbase.converter.ComboDetailConvert;
import com.example.projectbase.converter.ProductDetailConverter;
import com.example.projectbase.domain.dto.request.ComboDetailCreateDTO;
import com.example.projectbase.domain.dto.response.ComboCartDTO;
import com.example.projectbase.domain.dto.response.ComboDetailResposeDTO;
import com.example.projectbase.domain.entity.ComboDetailEntity;
import com.example.projectbase.repository.ComboDetailRepository;
import com.example.projectbase.repository.ComboRepository;
import com.example.projectbase.service.ComboDetailService;
import com.example.projectbase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComboDetailServiceImpl implements ComboDetailService {
    @Autowired
   ComboDetailConvert comboDetailConvert;
    @Autowired
    ComboDetailRepository comboDetailRepository;
    @Autowired
    ProductDetailConverter productDetailConverter;
    @Autowired
    ComboRepository comboRepository;
    @Autowired
    CartComboConverter cartComboConverter;
    @Autowired
    UserService userService;
    @Override
    public ComboDetailResposeDTO create(ComboDetailCreateDTO comboDetailCreateDTO) {
        ComboDetailEntity comboDetailEntity=comboDetailConvert.convertDTOToEntity(comboDetailCreateDTO);
        return comboDetailConvert.convertEntityToDTO(comboDetailRepository.save(comboDetailEntity));
    }

    @Override
    public ComboCartDTO findByComboId(Long comboId) {
        List<ComboDetailEntity> list=comboDetailRepository.findByComboId(comboId);
        if(list.size()>0){
            return cartComboConverter.convertEntityToDTO(comboDetailRepository.findByComboId(comboId).get(0));
        }
        return null;
    }

    @Override
    public ComboCartDTO addToCart(Long id) {
        ComboDetailEntity comboDetailEntity=comboDetailRepository.findById(id).get();
        comboDetailEntity.setCartEntity(userService.getCurrentUser().getCartEntity());
        return cartComboConverter.convertEntityToDTO(comboDetailRepository.save(comboDetailEntity));
    }
    @Override
    public void deleteById(Long id) {
        if(comboDetailRepository.findById(id).isPresent()){
            comboDetailRepository.deleteById(id);
        }
        else{
            throw new NullPointerException("Khong tim thay id!");
        }

    }
}
