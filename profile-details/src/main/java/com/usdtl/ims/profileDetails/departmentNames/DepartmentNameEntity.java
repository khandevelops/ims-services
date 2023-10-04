package com.usdtl.ims.profileDetails.departmentNames;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "department_names")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class DepartmentNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mapping")
    private String mapping;
    @Column(name = "hasInventory")
    private Boolean hasInventory;
}
