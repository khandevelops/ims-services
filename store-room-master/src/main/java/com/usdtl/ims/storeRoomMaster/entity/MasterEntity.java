package com.usdtl.ims.storeRoomMaster.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.usdtl.ims.common.exceptions.constants.Category;
import com.usdtl.ims.storeRoomMaster.StoreRoomMasterEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Table(name = "master")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MasterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "item")
    private String item;
    @Column(name = "manufacturer")
    private String manufacturer;
    @Column(name = "partNumber")
    private String partNumber;
    @Column(name = "recentCN")
    private String recentCN;
    @Column(name = "recentVendor")
    private String recentVendor;
    @Column(name = "fisherCN")
    private String fisherCN;
    @Column(name = "vwrCN")
    private String vwrCN;
    @Column(name = "labSourceCN")
    private String labSourceCN;
    @Column(name = "otherCN")
    private String otherCN;
    @Column(name = "purchaseUnit")
    private String purchaseUnit;
    @Column(name = "unitPrice")
    private Double unitPrice;
    @Column(name = "category")
    private String category;
    @Column(name = "comment")
    private String comment;
    @Column(name = "itemType")
    private String itemType;
    @Column(name = "itemGroup")
    private String itemGroup;
    @Column(name = "drugClass")
    private String drugClass;
    @OneToMany(mappedBy = "masterItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StoreRoomMasterEntity> storeRoomMasterItem;
}
