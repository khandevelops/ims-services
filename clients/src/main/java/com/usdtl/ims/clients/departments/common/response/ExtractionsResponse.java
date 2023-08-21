package com.usdtl.ims.clients.departments.common.response;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Builder
public record ExtractionsResponse(
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
