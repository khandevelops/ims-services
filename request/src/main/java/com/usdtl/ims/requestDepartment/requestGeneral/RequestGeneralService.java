package com.usdtl.ims.requestDepartment.requestGeneral;

import com.usdtl.ims.clients.MasterItemRequest;
import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.clients.ConfirmationEmailClient;
import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.common.constants.Confirmation;
import com.usdtl.ims.common.constants.Status;

import com.usdtl.ims.requestDepartment.master.MasterEntity;
import com.usdtl.ims.requestDepartment.master.MasterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestGeneralService {

    private RequestGeneralRepository repository;
    private MasterRepository masterRepository;
    private ConfirmationEmailClient confirmationEmailClient;

    public Page<RequestGeneralEntity> getRequestItemsByPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest);
    }
    public Page<RequestGeneralEntity> getRequestCompletedItemsByPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<RequestGeneralEntity> requestResponse = repository.findByConfirmation(Confirmation.COMPLETE, pageRequest);
        return requestResponse;
    }

    public Page<RequestGeneralEntity> getRequestPendingItemsByPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<RequestGeneralEntity> requestResponse = repository.findByConfirmation(Confirmation.WAITING, pageRequest);
        return requestResponse;
    }

    public RequestGeneralEntity updateRequestItemById(RequestGeneralEntity request) {
        RequestGeneralEntity storeRoomRequestItem = repository.findById(request.getId())
                .orElseThrow(() ->  new NotFoundException("Item associated with id: " + request.getId() + " not found"));
        return storeRoomRequestItem;
    }

    public String createRequestItem(List<RequestGeneralEntity> requests) {

        requests.forEach(request -> {
            MasterEntity masterItem = masterRepository
                    .findById(request.getMasterItem()
                            .getId()).orElseThrow(() -> new NotFoundException("Could not find the item"));
            RequestGeneralEntity storeRoomRequest = RequestGeneralEntity.builder()
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
            RequestGeneralEntity storeRoomRequestItem = repository.save(storeRoomRequest);
        });
        return "SUCCESS";
    }

    public RequestGeneralEntity updateRequestItem(RequestGeneralEntity request) {
        RequestGeneralEntity requestItem = repository.findById(request.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
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

    public List<RequestItemRequest> confirmRequestItems(List<RequestGeneralEntity> request) {
        List<RequestItemRequest> requestClientItems = new ArrayList<>();

        request.forEach(item -> {
            RequestGeneralEntity requestItem = repository.findById(item.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
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
}
