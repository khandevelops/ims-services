package com.usdtl.ims.requestMaster.requestMasterStoreRoom;

import com.usdtl.ims.clients.ConfirmationEmailClient;
import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.common.constants.Confirmation;
import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.requestMaster.master.MasterEntity;
import com.usdtl.ims.requestMaster.master.MasterRepository;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedAdminRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import com.usdtl.ims.requestMaster.requestMasterGeneral.RequestMasterGeneralEntity;
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
public class RequestMasterStoreRoomService {

    private RequestMasterStoreRoomRepository repository;
    private final MasterRepository masterRepository;
    private ConfirmationEmailClient confirmationEmailClient;

    public Page<RequestMasterStoreRoomEntity> getRequestItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public Page<RequestMasterStoreRoomEntity> getRequestCompletedItemsByPage(Integer page) {
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<RequestMasterStoreRoomEntity> storeRoomRequestResponse = repository.findByConfirmation(Confirmation.COMPLETE, pageRequest);
        return storeRoomRequestResponse;
    }

    public Page<RequestMasterStoreRoomEntity> getRequestPendingItemsByPage(Integer page) {
        Pageable pageRequest = PageRequest.of(page, 10);
        Page<RequestMasterStoreRoomEntity> storeRoomRequestResponse = repository.findByConfirmation(Confirmation.WAITING, pageRequest);
        return storeRoomRequestResponse;
    }

    public RequestMasterStoreRoomEntity updateRequestItemById(RequestMasterStoreRoomEntity request) {
        RequestMasterStoreRoomEntity storeRoomRequestItem = repository.findById(request.getId())
                .orElseThrow(() ->  new NotFoundException("Item associated with id: " + request.getId() + " not found"));
        return storeRoomRequestItem;
    }

    public List<RequestMasterStoreRoomEntity> createRequestItem(List<RequestMasterStoreRoomEntity> requests) {
        List<RequestMasterStoreRoomEntity> createdRequestItems = new ArrayList<>();

        requests.forEach(request -> {
            MasterEntity masterItem = masterRepository
                    .findById(request.getMasterItem()
                            .getId()).orElseThrow(() -> new NotFoundException("Could not find the item"));
            RequestMasterStoreRoomEntity requestItem = RequestMasterStoreRoomEntity.builder()
                    .order_quantity(request.getOrder_quantity())
                    .department(request.getDepartment())
                    .status(request.getStatus())
                    .location(request.getLocation())
                    .time_requested(new Date())
                    .time_updated(null)
                    .confirmation(request.getConfirmation())
                    .user(request.getUser())
                    .comment(request.getComment())
                    .build();
            requestItem.setMasterItem(masterItem);
            createdRequestItems.add(requestItem);

            RequestMasterStoreRoomEntity storeRoomRequestItem = repository.save(requestItem);
        });
        return createdRequestItems;
    }

    public RequestMasterStoreRoomEntity updateRequestItem(RequestMasterStoreRoomEntity request) {
        RequestMasterStoreRoomEntity requestItem = repository.findById(request.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
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

    public List<RequestItemRequest> confirmRequestItems(List<RequestItemRequest> request) {
        List<RequestItemRequest> requestClientItems = new ArrayList<>();

        request.forEach(item -> {
            RequestMasterStoreRoomEntity requestItem = repository.findById(item.masterItem().id()).orElseThrow(() -> new NotFoundException("cannot find this item"));
            requestItem.setConfirmation(Confirmation.COMPLETE);

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
        List<RequestMasterStoreRoomEntity> requestMasterItems = repository.findAll(pageRequest).getContent();

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
        List<RequestMasterStoreRoomEntity> requestMasterItems = repository.findAll(pageRequest).getContent();

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
                    .build();
            requestMasterTransformedItems.add(requestMasterTransformedItem);
        });

        return new PageImpl<>(requestMasterTransformedItems, pageRequest, requestMasterItemsCount);
    }
}
