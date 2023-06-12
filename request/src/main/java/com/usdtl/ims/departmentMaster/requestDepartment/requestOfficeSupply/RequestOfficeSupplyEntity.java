package com.usdtl.ims.departmentMaster.requestDepartment.requestOfficeSupply;

import com.usdtl.ims.departmentMaster.requestDepartment.master.MasterEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "office_supply")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestOfficeSupplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private MasterEntity masterItem;
}
