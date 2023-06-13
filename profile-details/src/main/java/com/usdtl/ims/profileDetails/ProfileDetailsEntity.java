package com.usdtl.ims.profileDetails;

import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.constants.Permission;
import com.usdtl.ims.common.exceptions.constants.Role;
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