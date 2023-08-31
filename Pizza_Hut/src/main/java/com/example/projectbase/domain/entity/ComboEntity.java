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
@Table(name = "combo")
public class ComboEntity extends BaseEntity {
    @Column(nullable = false)
    private String name;

    private String image;

    private String description;


    @Column(nullable =  false)
    private Long price;

    @ManyToOne
    @JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "FK_COMBO_CATEGORY"))
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "comboEntity", cascade = CascadeType.ALL)
    private List<ComboDetailEntity> comboDetailEntities=new ArrayList<>();

    @OneToMany(mappedBy = "comboEntity",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ComboItemEntity> comboItemEntities=new ArrayList<>();
}
