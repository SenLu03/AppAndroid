package com.example.projectbase.service;

import com.example.projectbase.domain.dto.response.SizeResponseDTO;

import java.util.List;

public interface SizeService {
    public List<SizeResponseDTO> findAll();
}
