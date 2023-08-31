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
@Table(name = "cart")
public class CartEntity extends BaseEntity {
    @OneToOne(mappedBy = "cartEntity",cascade = CascadeType.ALL)
    private UserEntity userEntity;

    @OneToOne(mappedBy = "cartEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private OrderEntity orderEntity;
    @OneToMany(mappedBy = "cartEntity", cascade =CascadeType.ALL )
    private List<ProductDetailEntity> productDetailEntities=new ArrayList<>();

    @OneToMany(mappedBy = "cartEntity", cascade =CascadeType.ALL )
    private List<ComboDetailEntity> comboDetailEntities=new ArrayList<>();


}
