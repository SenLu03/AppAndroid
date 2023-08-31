package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.request.CartCreateDTO;
import com.example.projectbase.service.CartService;
import com.example.projectbase.service.ComboDetailService;
import com.example.projectbase.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    ComboDetailService comboDetailService;
    @Autowired
    ProductDetailService productDetailService;
    @GetMapping("")
    public ResponseEntity<?> create(){
        return VsResponseUtil.success(cartService.findCart()) ;
    }
    @PostMapping("/product/{id}")
    public ResponseEntity<?> addProduct(@PathVariable Long id){

        return VsResponseUtil.success(productDetailService.addToCart(id)) ;
    }
    @PostMapping("/combo/{id}")
    public ResponseEntity<?> addCombo(@PathVariable Long id){
        return VsResponseUtil.success(comboDetailService.addToCart(id));
    }
    @DeleteMapping("/product/{id}")
    public  ResponseEntity<?> deleteProduct(@PathVariable Long id){
        productDetailService.deleteById(id);
        return VsResponseUtil.success("xoa thanh cong");
    }
    @DeleteMapping("/combo/{id}")
    public  ResponseEntity<?> deleteCombo(@PathVariable Long id){
        comboDetailService.deleteById(id);
        return VsResponseUtil.success("xoa thanh cong");
    }
}
