package com.example.projectbase.service.impl;

import com.example.projectbase.converter.SizeConverter;
import com.example.projectbase.domain.dto.response.SizeResponseDTO;
import com.example.projectbase.repository.SizeRepository;
import com.example.projectbase.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    SizeRepository sizeRepository;
    @Autowired
    SizeConverter sizeConverter;
    @Override
    public List<SizeResponseDTO> findAll() {
        return sizeConverter.converListEntityToListDTO(sizeRepository.findAll());
    }
}
