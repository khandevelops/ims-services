package com.usdtl.ims.requestDepartment.requestGeneral;
import com.usdtl.ims.requestDepartment.master.MasterEntity;
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
    @JoinColumn(name = "itemId")
    private MasterEntity masterItem;
}
