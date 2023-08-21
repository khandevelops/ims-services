package com.usdtl.ims.storeRoom.requestAndResponse;

import com.usdtl.ims.clients.responseRecord.StoreRoomResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record StoreRoomPageableResponse(
        List<StoreRoomResponse> content,
        Integer totalPages,
        Long totalElements,
        Integer size,
        Boolean first,
        Boolean last,
        Boolean empty,
        Integer number,
        Integer numberOfElements,
        Boolean sorted
) { }
