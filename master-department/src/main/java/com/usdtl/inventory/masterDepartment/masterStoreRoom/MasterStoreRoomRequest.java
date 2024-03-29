package com.usdtl.inventory.masterDepartment.masterStoreRoom;

import com.usdtl.inventory.masterDepartment.entities.ShippingEntity;
import com.usdtl.inventory.masterDepartment.entities.StoreRoomEntity;

import java.util.List;

public record MasterStoreRoomRequest(
        Integer id,
        String item,
        String manufacturer,
        String part_number,
        String recent_cn,
        String recent_vendor,
        String fisher_cn,
        String vwr_cn,
        String lab_source_cn,
        String next_advance_cn,
        String purchase_unit,
        Double average_unit_price,
        String category,
        String comments,
        String type,
        String group,
        List<StoreRoomEntity> storeRoomItems
) {
}
