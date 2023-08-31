package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.domain.dto.request.ComboCreateDTO;
import com.example.projectbase.domain.dto.request.OrderCreateDTO;
import com.example.projectbase.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody OrderCreateDTO orderCreateDTO){

        return VsResponseUtil.success(orderService.create(orderCreateDTO));
    }
    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return VsResponseUtil.success(orderService.findAll());
    }
}
