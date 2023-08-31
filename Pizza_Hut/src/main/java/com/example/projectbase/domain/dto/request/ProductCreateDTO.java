package com.example.projectbase.domain.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateDTO {

    private Long id;

    private String description;

    private MultipartFile image;

    @NotBlank(message = "Name is not empty and null")
    private String name;

    private int price;

    @NotNull(message = "categoryid is not null")
    private Long categoryId;


}
