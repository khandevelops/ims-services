package com.usdtl.inventory.masterDepartment.masterScreening;
import com.usdtl.ims.common.constants.Category;
import com.usdtl.inventory.masterDepartment.entities.ExtractionsEntity;
import com.usdtl.inventory.masterDepartment.entities.ScreeningEntity;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Builder
@Table(name = "master")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class MasterScreeningEntity {
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
    private String other_cn;
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
    private List<ScreeningEntity> departmentItems;
}
