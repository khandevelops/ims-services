package com.usdtl.ims.departmentMaster.storeRoom;

import com.usdtl.ims.departmentMaster.storeRoom.entity.MasterEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "store_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StoreRoomEntity {
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
