package com.example.projectbase.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComboCartDTO {
    private Long id;
    private String name;
    private Long price;
    private List<ProductDetailResponseDTO> productDetailResponseDTOS = new ArrayList<>();
}
