package com.usdtl.ims.clients.responseClass;

import com.usdtl.ims.common.constants.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class DepartmentMasterResponseTest {
    Integer id;
    Integer item_id;
    String item;
    String purchase_unit;
    String part_number;
    String recent_cn;
    String recent_vendor;
    String location;
    Long total_quantity;
    String usage_level;
    Integer min_quantity;
    Integer max_quantity;
    Integer order_quantity;
    Double unit_price;
    Double total_price;
    String lot_number;
    Category category;
    String comment;

    public DepartmentMasterResponseTest(Integer id, Integer item_id, String item, String purchase_unit, String part_number, String recent_cn, String recent_vendor, String location, Long total_quantity, String usage_level, Integer min_quantity, Integer max_quantity, Double unit_price, String lot_number, Category category, String comment) {
        this.id = id;
        this.item_id = item_id;
        this.item = item;
        this.purchase_unit = purchase_unit;
        this.part_number = part_number;
        this.recent_cn = recent_cn;
        this.recent_vendor = recent_vendor;
        this.location = location;
        this.total_quantity = total_quantity;
        this.usage_level = usage_level;
        this.min_quantity = min_quantity;
        this.max_quantity = max_quantity;
        this.unit_price = unit_price;
        this.lot_number = lot_number;
        this.category = category;
        this.comment = comment;
    }

    public DepartmentMasterResponseTest(Integer id, Integer item_id, String item, String purchase_unit, String part_number, String recent_cn, String recent_vendor, String location, String usage_level, Integer min_quantity, Integer max_quantity, Double unit_price, String lot_number, Category category, String comment) {
        this.id = id;
        this.item_id = item_id;
        this.item = item;
        this.purchase_unit = purchase_unit;
        this.part_number = part_number;
        this.recent_cn = recent_cn;
        this.recent_vendor = recent_vendor;
        this.location = location;
        this.usage_level = usage_level;
        this.min_quantity = min_quantity;
        this.max_quantity = max_quantity;
        this.unit_price = unit_price;
        this.lot_number = lot_number;
        this.category = category;
        this.comment = comment;
    }

    public DepartmentMasterResponseTest(Integer id, Integer item_id, String item, String purchase_unit, String part_number, String recent_cn, String recent_vendor, String location, String usage_level, Integer min_quantity, Integer max_quantity, Double unit_price, Double total_price, String lot_number, Category category, String comment) {
        this.id = id;
        this.item_id = item_id;
        this.item = item;
        this.purchase_unit = purchase_unit;
        this.part_number = part_number;
        this.recent_cn = recent_cn;
        this.recent_vendor = recent_vendor;
        this.location = location;
        this.usage_level = usage_level;
        this.min_quantity = min_quantity;
        this.max_quantity = max_quantity;
        this.total_price = total_price;
        this.unit_price = unit_price;
        this.lot_number = lot_number;
        this.category = category;
        this.comment = comment;
    }

    public DepartmentMasterResponseTest(Integer id, Integer item_id, String item, String purchase_unit, String part_number, String recent_cn, String recent_vendor, String location, Long total_quantity, String usage_level, Integer min_quantity, Integer max_quantity, Double unit_price, Double total_price, String lot_number, Category category, String comment) {
        this.id = id;
        this.item_id = item_id;
        this.item = item;
        this.purchase_unit = purchase_unit;
        this.part_number = part_number;
        this.recent_cn = recent_cn;
        this.recent_vendor = recent_vendor;
        this.location = location;
        this.total_quantity = total_quantity;
        this.usage_level = usage_level;
        this.min_quantity = min_quantity;
        this.max_quantity = max_quantity;
        this.unit_price = unit_price;
        this.total_price = total_price;
        this.lot_number = lot_number;
        this.category = category;
        this.comment = comment;
    }
}
