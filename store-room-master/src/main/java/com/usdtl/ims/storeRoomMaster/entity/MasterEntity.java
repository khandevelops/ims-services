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
    @Column(name = "part_number")
    private String part_number;
    @Column(name = "recent_cn")
    private String recent_cn;
    @Column(name = "recent_vendor")
    private String recent_vendor;
    @Column(name = "fisher_cn")
    private String fisher_cn;
    @Column(name = "vwr_cn")
    private String vwr_cn;
    @Column(name = "lab_source_cn")
    private String lab_source_cn;
    @Column(name = "other_cn")
    private String next_advance_cn;
    @Column(name = "purchase_unit")
    private String purchase_unit;
    @Column(name = "unit_price")
    private Double unit_price;
    @Column(name = "category")
    private Category category;
    @Column(name = "comment")
    private String comment;
    @Column(name = "[type]")
    private String type;
    @Column(name = "[group]")
    private String group;
    @Column(name = "drug_class")
    private String drug_class;
    @OneToMany(mappedBy = "masterItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StoreRoomMasterEntity> storeRoomMasterItem;
}