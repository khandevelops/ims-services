package com.usdtl.ims.clients.responseClass;

import com.usdtl.ims.common.exceptions.constants.Category;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
@Builder
public class DepartmentMasterResponseTest {
    String item;
    String purchase_unit;
    String part_number;
    String recent_cn;
    String recent_vendor;
    Double average_unit_price;
    Category category;
    String comment;
    Integer master_item_id;
    Integer department_item_id;
    String usage_level;
    Integer min_quantity;
    Integer max_quantity;
    String lot_number;
    String location;
    Date received_date;
    Date expirations_date;
    Integer quantity;
    Integer order_quantity;
    Long total_quantity;
    Double total_price;

    public DepartmentMasterResponseTest(String item, String purchase_unit, String part_number, String recent_cn, String recent_vendor, Double average_unit_price, Category category, String comment, Integer master_item_id, String usage_level, Integer min_quantity, Integer max_quantity, Long total_quantity) {
        this.item = item;
        this.purchase_unit = purchase_unit;
        this.part_number = part_number;
        this.recent_cn = recent_cn;
        this.recent_vendor = recent_vendor;
        this.average_unit_price = average_unit_price;
        this.category = category;
        this.comment = comment;
        this.master_item_id = master_item_id;
        this.usage_level = usage_level;
        this.min_quantity = min_quantity;
        this.max_quantity = max_quantity;
        this.total_quantity = total_quantity;
    }

    public DepartmentMasterResponseTest(String item, String purchase_unit, String part_number, String recent_cn, String recent_vendor, Double average_unit_price, Category category, String comment, Integer master_item_id, Integer department_item_id, String usage_level, Integer min_quantity, Integer max_quantity, String lot_number, String location, Date received_date, Date expirations_date, Integer quantity, Long total_quantity) {
        this.item = item;
        this.purchase_unit = purchase_unit;
        this.part_number = part_number;
        this.recent_cn = recent_cn;
        this.recent_vendor = recent_vendor;
        this.average_unit_price = average_unit_price;
        this.category = category;
        this.comment = comment;
        this.master_item_id = master_item_id;
        this.department_item_id = department_item_id;
        this.usage_level = usage_level;
        this.min_quantity = min_quantity;
        this.max_quantity = max_quantity;
        this.lot_number = lot_number;
        this.location = location;
        this.received_date = received_date;
        this.expirations_date = expirations_date;
        this.quantity = quantity;
        this.total_quantity = total_quantity;
    }
}
