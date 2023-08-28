package com.usdtl.ims.clients.departments.common.response;

import lombok.Builder;

import java.util.Date;

@Builder
public record DepartmentResponse(
    Integer id,
    String location,
    Integer quantity,
    Integer minimumQuantity,
    Integer maximumQuantity,
    String usageLevel,
    String lotNumber,
    Date expirationDate,
    Date receivedDate,
    Integer itemId
) {
}
