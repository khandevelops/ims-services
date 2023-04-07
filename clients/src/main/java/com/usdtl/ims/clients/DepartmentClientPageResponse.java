package com.usdtl.ims.clients;

import lombok.Builder;

import java.util.List;

@Builder
public record DepartmentClientPageResponse(
        List<DepartmentMasterResponse> content,
        Integer totalPages,
        Long totalElements,
        Integer size,
        Boolean first,
        Boolean last,
        Boolean empty,
        Integer number,
        Integer numberOfElements,
        Boolean sorted
) {
}
