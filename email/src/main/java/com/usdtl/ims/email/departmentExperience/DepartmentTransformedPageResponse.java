package com.usdtl.ims.email.departmentExperience;

import com.usdtl.ims.clients.DepartmentMasterResponse;
import lombok.Builder;

import java.util.List;

@Builder
public record DepartmentTransformedPageResponse(
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
