package com.usdtl.ims.master.entities;

import com.usdtl.ims.master.MasterEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "store_room")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RequestStoreRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "item_id")
    private MasterEntity masterItem;
}
