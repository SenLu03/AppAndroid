package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.request.ComboCreateDTO;
import com.example.projectbase.domain.dto.request.ComboDetailCreateDTO;
import com.example.projectbase.domain.dto.request.ProductCreateDTO;
import com.example.projectbase.service.ComboDetailService;
import com.example.projectbase.service.ComboService;
import com.example.projectbase.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/combo")
public class ComboController {
    @Autowired
    private ComboService comboService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ComboDetailService comboDetailService;
    @PostMapping("")
    public ResponseEntity<?> create( @ModelAttribute ComboCreateDTO comboCreateDTO){
        return VsResponseUtil.success(comboService.create(comboCreateDTO)) ;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> findByCategoryId(@PathVariable Long categoryId){
        return VsResponseUtil.success(comboService.findByCategoryId(categoryId));
    }

    @GetMapping("/products")
    public ResponseEntity<?> findByCombo(@RequestParam Long comboId,@RequestParam Long categoryId){
        return VsResponseUtil.success(productService.findByCombo(comboId,categoryId));
    }
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestBody ComboDetailCreateDTO comboDetailCreateDTO){
        return VsResponseUtil.success(comboDetailService.create(comboDetailCreateDTO)) ;
    }
    @GetMapping("/product_detail/{id}")
    public ResponseEntity<?> findByComboId(@PathVariable Long id){
        return VsResponseUtil.success(comboDetailService.findByComboId(id)) ;
    }


}
