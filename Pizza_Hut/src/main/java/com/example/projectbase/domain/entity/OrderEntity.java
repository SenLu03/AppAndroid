package com.example.projectbase.domain.entity;

import com.example.projectbase.domain.entity.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @Column
    private Integer percent;

//    @Column(nullable = false, unique = false)
    private Date order_date;

    @Column
    private Date delivery_date;

    @Column(nullable = false)
    private String delivery_address;

    @Column(nullable = false)
    private Long total_money;

    @Column(nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_ORDER_USER"))
    private UserEntity userEntity;

    @OneToOne
    @JoinColumn(name = "cart_id")
    private CartEntity cartEntity;

}
