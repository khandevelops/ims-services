package com.usdtl.ims.requestDepartment.requestGeneral;

import com.usdtl.ims.common.constants.Confirmation;
import com.usdtl.ims.common.constants.Department;
import com.usdtl.ims.common.constants.Status;
import com.usdtl.ims.requestDepartment.master.MasterEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

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
