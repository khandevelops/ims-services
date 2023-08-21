package com.usdtl.ims.clients.responseRecord;


public record StoreRoomResponse(
        Integer id,
        String location,
        Integer quantity,
        Integer min_quantity,
        Integer max_quantity,
        String usage_level

) { }



