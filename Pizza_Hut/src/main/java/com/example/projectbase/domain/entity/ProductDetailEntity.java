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
@Table(name = "product_detail")
public class ProductDetailEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private SizeEntity sizeEntity;

    @Column(nullable = false)
    private Long price;
    private int quatity=0;

    @ManyToOne
    @JoinColumn(name = "cake_base_id")
    private CakeBaseEntity cakeBaseEntity;

    @OneToMany(mappedBy = "productDetailEntity", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ComboDetailEntity> comboDetailEntities=new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

}
