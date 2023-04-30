package com.usdtl.ims.users;

import com.usdtl.ims.common.constants.Category;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsersEntity {
    @Id
    @Column(name="id")
    private String id;
    @Column(name = "department")
    private String department;
    @Column(name = "role")
    private String role;
}
