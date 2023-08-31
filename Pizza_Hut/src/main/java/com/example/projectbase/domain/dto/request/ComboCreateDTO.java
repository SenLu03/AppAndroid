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
public class ComboCreateDTO {
    private Long id;

    @NotBlank(message = "Name is not empty and null")
    private String name;

    private MultipartFile image;

    private String description;

    private int price;

    @NotNull(message = "categoryid is not null")
    private Long categoryId;




}
