package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
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
@Table(name = "sizes")
public class SizeEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    private Long price;
    @OneToMany(mappedBy = "sizeEntity", cascade = CascadeType.ALL)
    private List<ProductDetailEntity> productDetailEntities=new ArrayList<>();
}
