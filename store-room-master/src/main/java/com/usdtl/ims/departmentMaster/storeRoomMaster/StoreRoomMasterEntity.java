package com.usdtl.ims.departmentMaster.storeRoomMaster;

import com.usdtl.ims.departmentMaster.storeRoomMaster.entity.MasterEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "store_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreRoomMasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "location")
    private String location;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "minimum_quantity")
    private Integer minimum_quantity;
    @Column(name = "maximum_quantity")
    private Integer maximum_quantity;
    @Column(name = "usage_level")
    private String usage_level;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item_id")
    private MasterEntity masterItem;
}
