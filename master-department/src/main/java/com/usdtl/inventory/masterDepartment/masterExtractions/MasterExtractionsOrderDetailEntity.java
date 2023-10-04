package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Table(name = "extractions_order_detail")
public class MasterExtractionsOrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "totalQuantity")
    private Integer totalQuantity;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @Column(name = "orderQuantity")
    private Integer orderQuantity;
    @OneToOne()
    @JoinColumn(name = "itemId")
    @JsonIgnore
    private MasterExtractionsEntity masterDepartmentEntity;
}
