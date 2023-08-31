package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "combo_detail")
public class ComboDetailEntity  extends BaseEntity {

    @Column(nullable = false)
    private Long price;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "combo_id")
    private ComboEntity comboEntity;

    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    private ProductDetailEntity productDetailEntity;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

}
