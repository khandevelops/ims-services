package com.usdtl.ims.storeRoom;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

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
    @Column(name = "itemId")
    private Integer itemId;
}
