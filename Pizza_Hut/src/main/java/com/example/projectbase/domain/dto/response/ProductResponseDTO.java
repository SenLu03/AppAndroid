package com.example.projectbase.domain.dto.response;


public class ProductResponseDTO {

    private Long id;

    private String productName;

    private Long categoryId;

    private String image;

    private String description;

    private String price;

    private String cakeBase;

    private String cakeSize;

    private Long cakeBaseId;

    private Long cakeSizeId;

    private int point;

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCakeBaseId() {
        return cakeBaseId;
    }

    public void setCakeBaseId(Long cakeBaseId) {
        this.cakeBaseId = cakeBaseId;
    }

    public Long getCakeSizeId() {
        return cakeSizeId;
    }

    public void setCakeSizeId(Long cakeSizeId) {
        this.cakeSizeId = cakeSizeId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
