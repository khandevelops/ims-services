package com.usdtl.ims.requestMaster.requestMasterGeneral;

import com.usdtl.ims.clients.ConfirmationEmailClient;
import com.usdtl.ims.clients.MasterItemRequest;
import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.common.constants.Confirmation;
import com.usdtl.ims.common.constants.Status;
import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.requestMaster.master.MasterEntity;
import com.usdtl.ims.requestMaster.master.MasterRepository;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedAdminRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<RequestMasterGeneralEntity> getRequestItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
    public Page<RequestMasterGeneralEntity> getRequestCompletedItemsByPage(Integer page) {
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<RequestMasterGeneralEntity> requestResponse = repository.findByConfirmation(Confirmation.COMPLETE, pageRequest);
        return requestResponse;
    }

    public Page<RequestMasterGeneralEntity> getRequestPendingItemsByPage(Integer page) {
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<RequestMasterGeneralEntity> requestResponse = repository.findByConfirmation(Confirmation.WAITING, pageRequest);
        return requestResponse;
    }

    public RequestMasterGeneralEntity updateRequestItemById(RequestMasterGeneralEntity request) {
        RequestMasterGeneralEntity storeRoomRequestItem = repository.findById(request.getId())
                .orElseThrow(() ->  new NotFoundException("Item associated with id: " + request.getId() + " not found"));
        return storeRoomRequestItem;
    }

    public String createRequestItem(List<RequestMasterGeneralEntity> requests) {

        requests.forEach(request -> {
            MasterEntity masterItem = masterRepository
                    .findById(request.getMasterItem()
                            .getId()).orElseThrow(() -> new NotFoundException("Could not find the item"));
            RequestMasterGeneralEntity storeRoomRequest = RequestMasterGeneralEntity.builder()
                    .order_quantity(request.getOrder_quantity())
                    .department(request.getDepartment())
                    .status(Status.PENDING)
                    .location(request.getLocation())
                    .time_requested(new Date())
                    .time_updated(null)
                    .confirmation(Confirmation.WAITING)
                    .user(request.getUser())
                    .comment(request.getComment())
                    .build();
            storeRoomRequest.setMasterItem(masterItem);
            RequestMasterGeneralEntity storeRoomRequestItem = repository.save(storeRoomRequest);
        });
        return "SUCCESS";
    }

    public RequestMasterGeneralEntity updateRequestItem(RequestMasterGeneralEntity request) {
        RequestMasterGeneralEntity requestItem = repository.findById(request.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
        requestItem.setOrder_quantity(request.getOrder_quantity());
        requestItem.setDepartment(request.getDepartment());
        requestItem.setStatus(request.getStatus());
        requestItem.setLocation(request.getLocation());
        requestItem.setTime_updated(new Date());
        requestItem.setConfirmation(request.getConfirmation());
        requestItem.setUser(request.getUser());
        requestItem.setComment(request.getComment());
        requestItem.setCustom_text(request.getCustom_text());
        repository.save(requestItem);
        return requestItem;
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
                    .next_advance_cn(item.getMasterItem().getLab_source_cn())
                    .purchase_unit(item.getMasterItem().getPurchase_unit())
                    .average_unit_price(item.getMasterItem().getAverage_unit_price())
                    .category(item.getMasterItem().getCategory())
                    .comments(item.getMasterItem().getComments())
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
                    .comment(requestItem.getComment())
                    .custom_text(requestItem.getCustom_text())
                    .masterItem(masterItem)
                    .build();

            repository.save(requestItem);
            requestClientItems.add(requestClientItem);
        });

        confirmationEmailClient.sendConfirmationEmail(requestClientItems);

        return requestClientItems;
    }

    public Page<RequestMasterTransformedAdminRequest> getRequestMasterTransformedItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<RequestMasterTransformedAdminRequest> requestMasterTransformedItems = new ArrayList<>();

        Long requestMasterItemsCount = repository.count();
        List<RequestMasterGeneralEntity> requestMasterItems = repository.findAll(pageRequest).getContent();

        requestMasterItems.forEach(item -> {
            RequestMasterTransformedAdminRequest requestMasterTransformedItem = RequestMasterTransformedAdminRequest.builder()
                    .item(item.getMasterItem().getItem())
                    .master_item_id(item.getMasterItem().getId())
                    .request_item_id(item.getId())
                    .recent_cn(item.getMasterItem().getRecent_cn())
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

    public Page<RequestMasterTransformedDepartmentRequest> getRequestMasterTransformedDepartmentItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<RequestMasterTransformedDepartmentRequest> requestMasterTransformedItems = new ArrayList<>();

        Long requestMasterItemsCount = repository.count();
        List<RequestMasterGeneralEntity> requestMasterItems = repository.findAll(pageRequest).getContent();

        requestMasterItems.forEach(item -> {
            RequestMasterTransformedDepartmentRequest requestMasterTransformedItem = RequestMasterTransformedDepartmentRequest.builder()
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
