package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "extractions_order_detail")
public class MasterExtractionsOrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "totalQuantity")
    private Integer totalQuantity;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @Column(name = "orderQuantity")
    private Integer orderQuantity;
    @OneToOne
    @MapsId
    @JoinColumn(name = "itemId")
    @JsonIgnore
    private MasterExtractionsEntity masterExtractionsItem;
}