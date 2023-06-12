package com.usdtl.ims.departmentMaster.requestDepartment.requestStoreRoom;

import com.usdtl.ims.departmentMaster.requestDepartment.request.RequestDepartmentResponse;
import lombok.AllArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestStoreRoomService {

    private RequestStoreRoomRepository repository;

    public Page<RequestDepartmentResponse> getRequestTranformedItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<RequestDepartmentResponse> requestTransformedItems = new ArrayList<>();
        Long requestItemCount = repository.count();
        List<RequestStoreRoomEntity> requestMasterItems = repository.findAll(pageRequest).getContent();
        requestMasterItems.forEach(item -> {
            RequestDepartmentResponse requestMasterTransformedItem = RequestDepartmentResponse.builder()
                    .item(item.getMasterItem().getItem())
                    .master_item_id(item.getMasterItem().getId())
                    .request_item_id(item.getId())
                    .recent_cn(item.getMasterItem().getRecent_cn())
                    .purchase_unit(item.getMasterItem().getPurchase_unit())
                    .part_number(item.getMasterItem().getPart_number())
                    .comment(item.getMasterItem().getComment())
                    .build();
            requestTransformedItems.add(requestMasterTransformedItem);
        });

        return new PageImpl<>(requestTransformedItems, pageRequest, requestItemCount);
    }
}
