package com.example.projectbase.domain.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class RoleRequestDTO {

    @NotBlank(message = "Name is not empty and null")
    private String name;

    @NotBlank(message = "Name is not empty and null")
    private String code;
}
