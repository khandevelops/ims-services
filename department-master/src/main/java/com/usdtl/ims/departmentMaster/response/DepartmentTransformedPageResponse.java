package com.usdtl.ims.departmentMaster.response;

import lombok.Builder;

import java.util.List;

@Builder
public record DepartmentTransformedPageResponse(
        List<DepartmentTransformedResponse> content,
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
