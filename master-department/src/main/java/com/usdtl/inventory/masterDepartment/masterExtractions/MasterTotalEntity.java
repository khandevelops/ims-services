package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "master_total")
public class MasterTotalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "totalQuantity")
    private Integer totalQuantity;
    @Column(name = "totalPrice")
    private Double totalPrice;
    @OneToOne(mappedBy = "masterTotalItem")
    private MasterExtractionsEntity masterExtractionsItem;
}
