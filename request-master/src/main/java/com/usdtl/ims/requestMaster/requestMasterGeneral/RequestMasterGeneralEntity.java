package com.usdtl.ims.requestMaster.requestMasterGeneral;

import com.usdtl.ims.common.exceptions.constants.Confirmation;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.constants.Status;
import com.usdtl.ims.requestMaster.master.MasterEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Table(name = "general_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestMasterGeneralEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "detail")
    private String detail;
    @Column(name = "department")
    private Department department;
    @Column(name = "status")
    private Status status;
    @Column(name = "location")
    private String location;
    @CreationTimestamp
    @Column(name = "timeRequested")
    private Date timeRequested;
    @Column(name = "timeUpdated")
    private Date timeUpdated;
    @Column(name = "confirmation")
    private Confirmation confirmation;
    @Column(name = "user")
    private String user;
    @Column(name = "customText")
    private String customText;
    @ManyToOne
    @JoinColumn(name = "itemId")
    private MasterEntity masterItem;
}
