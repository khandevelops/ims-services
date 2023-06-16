package com.usdtl.ims.storeRoom.requestAndResponse;

import lombok.Builder;

@Builder
public record StoreRoomResponse(
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
        String comment
) { }
