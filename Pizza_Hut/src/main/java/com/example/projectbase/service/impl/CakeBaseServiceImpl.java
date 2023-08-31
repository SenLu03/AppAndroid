package com.example.projectbase.service.impl;

import com.example.projectbase.converter.CakeBaseConverter;
import com.example.projectbase.domain.dto.response.CakeBaseResponseDTO;
import com.example.projectbase.repository.CakeBaseRepository;
import com.example.projectbase.service.CakeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CakeBaseServiceImpl implements CakeBaseService {
    @Autowired
    CakeBaseConverter cakeBaseConverter;
    @Autowired
    CakeBaseRepository cakeBaseRepository;
    @Override
    public List<CakeBaseResponseDTO> findAll() {
        return cakeBaseConverter.converListEntityToListDTO(cakeBaseRepository.findAll());
    }
}
