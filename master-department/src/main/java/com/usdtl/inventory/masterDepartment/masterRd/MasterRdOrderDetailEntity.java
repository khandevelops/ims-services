package com.usdtl.inventory.masterDepartment.masterRd;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "rd_order_detail")
public class MasterRdOrderDetailEntity {
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
    @OneToOne
    @JoinColumn(name = "itemId")
    @JsonIgnore
    private MasterRdEntity masterDepartmentItem;
}
