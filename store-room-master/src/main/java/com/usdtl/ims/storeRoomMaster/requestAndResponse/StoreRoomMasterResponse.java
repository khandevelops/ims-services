package com.usdtl.ims.storeRoomMaster.requestAndResponse;

import lombok.Builder;

@Builder
public record StoreRoomMasterResponse (
        Integer store_room_item_id,
        Integer master_item_id,
        String item,
        String purchase_unit,
        String part_number,
        String recent_cn,
        String location,
        Integer total_quantity,
        String usage_level,
        Integer min_quantity,
        Integer max_quantity,
        Integer order_quantity,
        Double unit_price,
        Double total_price,
        String comments
) { }

//@Builder
//public class StoreRoomMasterResponse {
//    Integer store_room_item_id;
//    Integer master_item_id;
//    String item;
//    String purchase_unit;
//    String part_number;
//    String recent_cn;
//    String location;
//    Integer total_quantity;
//    String usage_level;
//    Integer min_quantity;
//    Integer max_quantity;
//    Integer order_quantity;
//    Double unit_price;
//    Double total_price;
//    String comments;
//}
