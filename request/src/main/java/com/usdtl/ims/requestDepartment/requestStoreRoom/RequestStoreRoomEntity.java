package com.usdtl.ims.requestDepartment.requestStoreRoom;

import com.usdtl.ims.requestDepartment.master.MasterEntity;
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
