package com.usdtl.ims.departmentMaster.storeRoom;

import com.usdtl.ims.departmentMaster.common.MasterEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "store_room")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@javax.persistence.Entity
@ToString
public class StoreRoomMasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "location")
    private String location;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "minimumQuantity")
    private Integer minimumQuantity;
    @Column(name = "maximumQuantity")
    private Integer maximumQuantity;
    @Column(name = "usageLevel")
    private String usageLevel;
    @Column(name = "lotNumber")
    private String lotNumber;
    @Column(name = "expirationDate")
    private Date expirationDate;
    @Column(name = "receivedDate")
    private Date receivedDate;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "itemId")
    private MasterEntity masterItem;
}
