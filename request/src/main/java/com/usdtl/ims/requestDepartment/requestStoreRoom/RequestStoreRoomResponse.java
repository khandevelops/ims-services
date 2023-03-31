package com.usdtl.ims.requestDepartment.requestStoreRoom;

import com.usdtl.ims.requestDepartment.constants.Confirmation;
import com.usdtl.ims.requestDepartment.constants.Status;

import java.util.Date;

public record RequestStoreRoomResponse(
        Integer id,
        Integer item_id,
        Integer order_quantity,
        String department,
        Status status,
        String location,
        Date time_requested,
        Date time_received,
        Confirmation confirmation,
        String user,
        String comment,
        String custom_text
) {
}
