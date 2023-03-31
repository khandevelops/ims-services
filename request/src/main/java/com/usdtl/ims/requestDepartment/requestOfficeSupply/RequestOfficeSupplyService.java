package com.usdtl.ims.requestDepartment.requestOfficeSupply;

import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.clients.ConfirmationEmailClient;
import com.usdtl.ims.common.constants.Confirmation;
import com.usdtl.ims.common.constants.Status;
import com.usdtl.ims.common.exceptions.NotFoundException;
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
public class RequestOfficeSupplyService {

    private RequestOfficeSupplyRepository repository;
    private final MasterRepository masterRepository;
    private ConfirmationEmailClient confirmationEmailClient;

    public Page<RequestOfficeSupplyEntity> getRequestItemsByPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest);
    }

    public Page<RequestOfficeSupplyEntity> getRequestCompletedItemsByPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<RequestOfficeSupplyEntity> requestResponse = repository.findByConfirmation(Confirmation.COMPLETE, pageRequest);
        return requestResponse;
    }

    public Page<RequestOfficeSupplyEntity> getRequestPendingItemsByPage(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);
        Page<RequestOfficeSupplyEntity> requestResponse = repository.findByConfirmation(Confirmation.WAITING, pageRequest);
        return requestResponse;
    }

    public RequestOfficeSupplyEntity updateRequestItemById(RequestOfficeSupplyEntity request) {
        RequestOfficeSupplyEntity storeRoomRequestItem = repository.findById(request.getId())
                .orElseThrow(() ->  new NotFoundException("Item associated with id: " + request.getId() + " not found"));
        return storeRoomRequestItem;
    }

    public String createRequestItem(List<RequestOfficeSupplyEntity> requests) {

        requests.forEach(request -> {
            MasterEntity masterItem = masterRepository
                    .findById(request.getMasterItem()
                            .getId()).orElseThrow(() -> new NotFoundException("Could not find the item"));
            RequestOfficeSupplyEntity storeRoomRequest = RequestOfficeSupplyEntity.builder()
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
            RequestOfficeSupplyEntity storeRoomRequestItem = repository.save(storeRoomRequest);
        });
        return "SUCCESS";
    }

    public RequestOfficeSupplyEntity updateRequestItem(RequestOfficeSupplyEntity request) {
        RequestOfficeSupplyEntity requestItem = repository.findById(request.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
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
            RequestOfficeSupplyEntity requestItem = repository.findById(item.masterItem().id()).orElseThrow(() -> new NotFoundException("cannot find this item"));
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
                    .build();

            repository.save(requestItem);
            requestClientItems.add(requestClientItem);
        });

        confirmationEmailClient.sendConfirmationEmail(requestClientItems);

        return requestClientItems;
    }

}
