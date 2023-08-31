package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.CategoryCreateDTO;
import com.example.projectbase.domain.dto.response.CategoryResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

public interface CategoryService {
    ResponseEntity<?> createCategory(@Valid CategoryCreateDTO categoryDTO, BindingResult bindingResult);
    List <CategoryResponseDTO> findAll();
    ResponseEntity<?> findOne(Long id);
    ResponseEntity<?> findByName(Integer page, Integer size, String nameProduct);
    ResponseEntity<?> updateCategory(Long id, CategoryCreateDTO categoryDTO, BindingResult bindingResult);
    void deleteCategory(Long id);
}
