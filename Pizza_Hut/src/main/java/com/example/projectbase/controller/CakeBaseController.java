package com.example.projectbase.controller;

import com.example.projectbase.base.VsResponseUtil;
import com.example.projectbase.service.CakeBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cakebase")
public class CakeBaseController {
    @Autowired
    CakeBaseService cakeBaseService;
    @GetMapping("")
    public ResponseEntity<?> findAll(){
        return VsResponseUtil.success(cakeBaseService.findAll());
    }
}
