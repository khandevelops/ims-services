package com.usdtl.ims.requestDepartment.requestOfficeSupply;

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
@Table(name = "office_supply_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestOfficeSupplyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "order_quantity")
    private Integer order_quantity;
    @Column(name = "department")
    private Department department;
    @Column(name = "status")
    private Status status;
    @Column(name = "location")
    private String location;
    @CreationTimestamp
    @Column(name = "time_requested")
    private Date time_requested;
    @Column(name = "time_updated")
    private Date time_updated;
    @Column(name = "confirmation")
    private Confirmation confirmation;
    @Column(name = "user")
    private String user;
    @Column(name = "comment")
    private String comment;
    @Column(name = "custom_text")
    private String custom_text;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private MasterEntity masterItem;
}
