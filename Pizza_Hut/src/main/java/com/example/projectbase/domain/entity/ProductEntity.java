package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "products")
public class ProductEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String image;

    @Column(columnDefinition = "TEXT")
    private String description;

    private int point;

    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_PRODUCT_CATEGORY"))
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL)
    private List<ProductDetailEntity> productDetailEntities = new ArrayList<>();

    @OneToMany(mappedBy = "productEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ComboItemEntity> comboItemEntities=new ArrayList<>();
}
