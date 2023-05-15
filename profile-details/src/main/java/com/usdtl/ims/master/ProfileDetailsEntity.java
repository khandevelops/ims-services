package com.usdtl.ims.master;

import com.usdtl.ims.common.constants.Department;
import com.usdtl.ims.common.constants.Permission;
import com.usdtl.ims.common.constants.Role;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Table(name = "profile_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDetailsEntity {
    @Id
    @Column(name="id")
    private String id;
    @Column(name = "department")
    private Department department;
    @Column(name = "role")
    private Role role;
    @Column(name = "permission")
    private Permission permission;
}
