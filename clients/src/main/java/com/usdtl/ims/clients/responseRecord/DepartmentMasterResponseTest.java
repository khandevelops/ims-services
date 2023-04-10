package com.usdtl.ims.clients.responseRecord;

import com.usdtl.ims.common.constants.Category;
import lombok.*;

import javax.persistence.NamedNativeQuery;


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
}
