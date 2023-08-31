package com.example.projectbase.service;

import com.example.projectbase.domain.dto.request.ProductCreateDTO;
import com.example.projectbase.domain.dto.request.ProductPointDTO;
import com.example.projectbase.domain.dto.request.ProductSearchPizzaDTO;
import com.example.projectbase.domain.dto.response.ProductResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

public interface ProductService {
    ResponseEntity<?> createProduct(@Valid ProductCreateDTO productCreateDTO,
                                    BindingResult bindingResult);
    ResponseEntity<?> findOne(Long id);
    ResponseEntity<?> updateProduct(ProductCreateDTO productDTO, BindingResult bindingResult);
    void deleteProduct(Long id);
    List<ProductResponseDTO> findWithPizza(ProductSearchPizzaDTO productSearchPizzaDTO);
    List<ProductResponseDTO> findByCombo(Long comboId,Long categoryId);
    List<ProductResponseDTO> findProductHavePoint();
    void changePoint(Long id);
}
