package com.example.projectbase.domain.dto.request;

public class ProductSearchPizzaDTO {

    private Long id;

    private Long categoryId;

    private String cakeBase;

    private String cakeSize;

    private Long cakeSizeId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getCakeSizeId() {
        return cakeSizeId;
    }

    public void setCakeSizeId(Long cakeSizeId) {
        this.cakeSizeId = cakeSizeId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCakeBase() {
        return cakeBase;
    }

    public void setCakeBase(String cakeBase) {
        this.cakeBase = cakeBase;
    }

    public String getCakeSize() {
        return cakeSize;
    }

    public void setCakeSize(String cakeSize) {
        this.cakeSize = cakeSize;
    }
}
