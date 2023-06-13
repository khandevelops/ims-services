package com.usdtl.inventory.masterDepartment.entities;

import com.usdtl.inventory.masterDepartment.masterStoreRoom.MasterStoreRoomEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Table(name = "store_room")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class StoreRoomEntity {
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
    @Column(name = "lot_number")
    private String lot_number;
    @Column(name = "expiration_date")
    private Date expiration_date;
    @Column(name = "received_date")
    private Date received_date;
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "item_id")
    private MasterStoreRoomEntity masterItem;
}