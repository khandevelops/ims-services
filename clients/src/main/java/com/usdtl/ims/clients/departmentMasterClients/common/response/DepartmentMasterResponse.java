package com.usdtl.ims.clients.departmentMasterClients.common.response;

import com.usdtl.ims.clients.masterClients.response.MasterResponse;
import lombok.Builder;

import java.util.Date;

@Builder
public record DepartmentMasterResponse(
    Integer id,
    String location,
    Integer quantity,
    Integer minimumQuantity,
    Integer maximumQuantity,
    String usageLevel,
    String lotNumber,
    Date expirationDate,
    Date receivedDate,
    MasterResponse masterItem
) {
}
