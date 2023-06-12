package com.usdtl.inventory.masterDepartment.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity;
import com.usdtl.inventory.masterDepartment.masterShipping.MasterShippingEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Table(name = "shipping")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShippingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "location")
    private String location;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "lot_number")
    private String lot_number;
    @Column(name = "expiration_date")
    private Date expiration_date;
    @Column(name = "received_date")
    private Date received_date;
    @Column(name = "usage_level")
    private String usage_level;
    @Column(name = "minimum_quantity")
    private Integer minimum_quantity;
    @Column(name = "maximum_quantity")
    private Integer maximum_quantity;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private MasterShippingEntity masterItem;
}
