package com.usdtl.ims.departments.screening;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "screening")
public class ScreeningEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "location")
    private String location;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "min_quantity")
    private Integer min_quantity;
    @Column(name = "max_quantity")
    private Integer max_quantity;
    @Column(name = "usage_level")
    private String usage_level;
    @Column(name = "lot_number")
    private String lot_number;
    @Column(name = "expiration_date")
    private Date expiration_date;
    @Column(name = "received_date")
    private Date received_date;
}
