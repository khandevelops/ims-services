package com.usdtl.ims.storeRoomMaster;

import com.usdtl.ims.storeRoomMaster.entity.MasterEntity;
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
    @Column(name = "min_quantity")
    private Integer min_quantity;
    @Column(name = "max_quantity")
    private Integer max_quantity;
    @Column(name = "usage_level")
    private String usage_level;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item_id")
    private MasterEntity masterItem;
}
