package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.request.ComboCreateDTO;
import com.example.projectbase.domain.dto.request.ProductDetailCreateDTO;
import com.example.projectbase.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/product_detail")
public class ProductDetailController {
    @Autowired
    private ProductDetailService productDetailService;
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody ProductDetailCreateDTO productDetailCreateDTO){
        return VsResponseUtil.success(productDetailService.create(productDetailCreateDTO));
    }

}
