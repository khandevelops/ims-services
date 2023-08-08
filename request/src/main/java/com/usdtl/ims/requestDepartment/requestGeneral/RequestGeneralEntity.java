package com.usdtl.ims.requestDepartment.requestGeneral;

import com.usdtl.ims.master.MasterEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "general")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestGeneralEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private MasterEntity masterItem;
}
