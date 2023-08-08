package com.usdtl.ims.requestMaster.requestMasterStoreRoom;

import com.usdtl.ims.common.exceptions.constants.Confirmation;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.constants.Status;
import com.usdtl.ims.master.MasterEntity;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Table(name = "store_room_request")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestMasterStoreRoomEntity {
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
    @Column(name = "time_requested")
    private Date time_requested;
    @Column(name = "time_updated")
    private Date time_updated;
    @Column(name = "confirmation")
    private Confirmation confirmation;
    @Column(name = "user")
    private String user;
    @Column(name = "custom_text")
    private String custom_text;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private MasterEntity masterItem;
}
