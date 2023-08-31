package com.example.projectbase.service;

import com.example.projectbase.domain.dto.response.CakeBaseResponseDTO;

import java.util.List;

public interface CakeBaseService {
    public List<CakeBaseResponseDTO> findAll();
}
