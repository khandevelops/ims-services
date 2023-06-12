package com.usdtl.ims.departmentMaster.requestMaster.requestMasterGeneral;

import com.usdtl.ims.clients.ConfirmationEmailClient;
import com.usdtl.ims.clients.MasterItemRequest;
import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.common.exceptions.constants.Confirmation;
import com.usdtl.ims.common.exceptions.constants.Status;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.departmentMaster.requestMaster.master.MasterEntity;
import com.usdtl.ims.departmentMaster.requestMaster.master.MasterRepository;
import com.usdtl.ims.departmentMaster.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import com.usdtl.ims.departmentMaster.requestMaster.request.RequestMasterTransformedDepartmentResponse;
import com.usdtl.ims.departmentMaster.requestMaster.request.RequestMasterTransformedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestMasterGeneralService {

    private RequestMasterGeneralRepository repository;
    private MasterRepository masterRepository;
    private ConfirmationEmailClient confirmationEmailClient;

    public List<RequestMasterTransformedDepartmentResponse> updateRequestItems(List<RequestMasterTransformedDepartmentRequest> requestItems) {
        List<RequestMasterTransformedDepartmentResponse> requestTransformedResponseItems = new ArrayList<>();
        requestItems.forEach(requestTransformedItem -> {
            RequestMasterGeneralEntity requestItem = repository.findById(requestTransformedItem.request_item_id()).orElseThrow(() -> new NotFoundException("cannot find this item"));
            requestItem.setQuantity(requestTransformedItem.quantity());
            requestItem.setDepartment(requestTransformedItem.department());
            requestItem.setStatus(requestTransformedItem.status());
            requestItem.setLocation(requestTransformedItem.location());
            requestItem.setTime_updated(new Date());
            requestItem.setConfirmation(requestTransformedItem.confirmation());
            requestItem.setUser(requestTransformedItem.user());
            requestItem.setDetail(requestTransformedItem.detail());
            requestItem.setCustom_text(requestTransformedItem.custom_text());
            repository.save(requestItem);

        });

        return requestTransformedResponseItems;
    }

    public List<RequestMasterTransformedResponse> createRequestItem(List<RequestMasterTransformedDepartmentRequest> requests) {
        List<RequestMasterTransformedResponse> requestTransformedItemsResponse = new ArrayList<>();

        requests.forEach(request -> {
            MasterEntity masterItem = masterRepository
                    .findById(request.master_item_id()).orElseThrow(() -> new NotFoundException("Could not find the item"));
            RequestMasterGeneralEntity storeRoomRequest = RequestMasterGeneralEntity.builder()
                    .quantity(request.quantity())
                    .department(request.department())
                    .user(request.user())
                    .detail(request.detail())
                    .custom_text(request.custom_text())
                    .location(request.location())
                    .status(Status.PENDING)
                    .time_requested(new Date())
                    .time_updated(new Date())
                    .confirmation(Confirmation.WAITING)
                    .build();
            storeRoomRequest.setMasterItem(masterItem);
            RequestMasterGeneralEntity storeRoomRequestItem = repository.save(storeRoomRequest);
        });
        return requestTransformedItemsResponse;
    }

    public RequestMasterGeneralEntity updateRequestItem(Integer id, RequestMasterTransformedDepartmentRequest requestItem) {
        RequestMasterGeneralEntity requestMasterItem = repository.findById(requestItem.request_item_id()).orElseThrow(() -> new NotFoundException("cannot find this item"));
        requestMasterItem.setQuantity(requestItem.quantity());
        requestMasterItem.setDepartment(requestItem.department());
        requestMasterItem.setStatus(requestItem.status());
        requestMasterItem.setLocation(requestItem.location());
        requestMasterItem.setTime_updated(new Date());
        requestMasterItem.setConfirmation(requestItem.confirmation());
        requestMasterItem.setUser(requestItem.user());
        requestMasterItem.setDetail(requestItem.detail());
        requestMasterItem.setCustom_text(requestItem.custom_text());
        repository.save(requestMasterItem);
        return requestMasterItem;
    }

    public List<RequestItemRequest> confirmRequestItems(List<RequestMasterGeneralEntity> request) {
        List<RequestItemRequest> requestClientItems = new ArrayList<>();

        request.forEach(item -> {
            RequestMasterGeneralEntity requestItem = repository.findById(item.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
            requestItem.setConfirmation(Confirmation.COMPLETE);

            MasterItemRequest masterItem = MasterItemRequest.builder()
                    .id(item.getMasterItem().getId())
                    .item(item.getMasterItem().getItem())
                    .manufacturer(item.getMasterItem().getManufacturer())
                    .part_number(item.getMasterItem().getPart_number())
                    .recent_cn(item.getMasterItem().getRecent_cn())
                    .recent_vendor(item.getMasterItem().getRecent_vendor())
                    .fisher_cn(item.getMasterItem().getFisher_cn())
                    .vwr_cn(item.getMasterItem().getVwr_cn())
                    .lab_source_cn(item.getMasterItem().getLab_source_cn())
                    .other_cn(item.getMasterItem().getLab_source_cn())
                    .purchase_unit(item.getMasterItem().getPurchase_unit())
                    .unit_price(item.getMasterItem().getUnit_price())
                    .category(item.getMasterItem().getCategory())
                    .comment(item.getMasterItem().getComment())
                    .type(item.getMasterItem().getType())
                    .group(item.getMasterItem().getGroup())
                    .build();

            RequestItemRequest requestClientItem = RequestItemRequest.builder()
                    .order_quantity(requestItem.getId())
                    .department(requestItem.getDepartment())
                    .status(requestItem.getStatus())
                    .location(requestItem.getLocation())
                    .time_updated(new Date())
                    .confirmation(requestItem.getConfirmation())
                    .user(requestItem.getUser())
                    .detail(requestItem.getDetail())
                    .custom_text(requestItem.getCustom_text())
                    .masterItem(masterItem)
                    .build();

            repository.save(requestItem);
            requestClientItems.add(requestClientItem);
        });

        confirmationEmailClient.sendConfirmationEmail(requestClientItems);

        return requestClientItems;
    }

    public Page<RequestMasterTransformedResponse> getRequestMasterTransformedItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<RequestMasterTransformedResponse> requestMasterTransformedItems = new ArrayList<>();

        Long requestMasterItemsCount = repository.count();
        List<RequestMasterGeneralEntity> requestMasterItems = repository.findAll(pageRequest).getContent();

        requestMasterItems.forEach(item -> {
            RequestMasterTransformedResponse requestMasterTransformedItem = RequestMasterTransformedResponse.builder()
                    .item(item.getMasterItem().getItem())
                    .master_item_id(item.getMasterItem().getId())
                    .request_item_id(item.getId())
                    .recent_cn(item.getMasterItem().getRecent_cn())
                    .purchase_unit(item.getMasterItem().getPurchase_unit())
                    .department(item.getDepartment())
                    .status(item.getStatus())
                    .quantity(item.getQuantity())
                    .time_requested(item.getTime_requested())
                    .time_update(item.getTime_updated())
                    .detail(item.getDetail())
                    .custom_text(item.getCustom_text())
                    .build();
            requestMasterTransformedItems.add(requestMasterTransformedItem);
        });

        return new PageImpl<>(requestMasterTransformedItems, pageRequest, requestMasterItemsCount);
    }

    public Page<RequestMasterTransformedDepartmentResponse> getRequestMasterCompleteTransformedItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<RequestMasterTransformedDepartmentResponse> requestMasterTransformedItems = new ArrayList<>();

        Long requestMasterItemsCount = repository.findByConfirmation(Confirmation.COMPLETE, pageRequest).getTotalElements();
        List<RequestMasterGeneralEntity> requestMasterItems = repository.findByConfirmation(Confirmation.COMPLETE, pageRequest).getContent();

        requestMasterItems.forEach(item -> {
            RequestMasterTransformedDepartmentResponse requestMasterTransformedItem = RequestMasterTransformedDepartmentResponse.builder()
                    .item(item.getMasterItem().getItem())
                    .master_item_id(item.getMasterItem().getId())
                    .request_item_id(item.getId())
                    .recent_cn(item.getMasterItem().getRecent_cn())
                    .purchase_unit(item.getMasterItem().getPurchase_unit())
                    .part_number(item.getMasterItem().getPart_number())
                    .detail(item.getDetail())
                    .status(item.getStatus())
                    .quantity(item.getQuantity())
                    .time_requested(item.getTime_requested())
                    .time_update(item.getTime_updated())
                    .custom_text(item.getCustom_text())
                    .build();

            requestMasterTransformedItems.add(requestMasterTransformedItem);
        });

        return new PageImpl<>(requestMasterTransformedItems, pageRequest, requestMasterItemsCount);
    }

    public Page<RequestMasterTransformedDepartmentResponse> getRequestMasterPendingTransformedItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<RequestMasterTransformedDepartmentResponse> requestMasterTransformedItems = new ArrayList<>();

        Long requestMasterItemsCount = repository.findByConfirmation(Confirmation.WAITING, pageRequest).getTotalElements();
        List<RequestMasterGeneralEntity> requestMasterItems = repository.findByConfirmation(Confirmation.WAITING, pageRequest).getContent();

        requestMasterItems.forEach(item -> {
            RequestMasterTransformedDepartmentResponse requestMasterTransformedItem = RequestMasterTransformedDepartmentResponse.builder()
                    .item(item.getMasterItem().getItem())
                    .master_item_id(item.getMasterItem().getId())
                    .request_item_id(item.getId())
                    .recent_cn(item.getMasterItem().getRecent_cn())
                    .purchase_unit(item.getMasterItem().getPurchase_unit())
                    .part_number(item.getMasterItem().getPart_number())
                    .detail(item.getDetail())
                    .status(item.getStatus())
                    .quantity(item.getQuantity())
                    .time_requested(item.getTime_requested())
                    .time_update(item.getTime_updated())
                    .custom_text(item.getCustom_text())
                    .build();

            requestMasterTransformedItems.add(requestMasterTransformedItem);
        });

        return new PageImpl<>(requestMasterTransformedItems, pageRequest, requestMasterItemsCount);
    }
}
