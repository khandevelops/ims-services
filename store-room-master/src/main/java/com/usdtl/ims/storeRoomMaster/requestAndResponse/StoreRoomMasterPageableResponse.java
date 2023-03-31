package com.usdtl.ims.storeRoomMaster.requestAndResponse;

import lombok.Builder;

import java.util.List;

@Builder
public record StoreRoomMasterPageableResponse (
        List<StoreRoomMasterResponse> content,
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
