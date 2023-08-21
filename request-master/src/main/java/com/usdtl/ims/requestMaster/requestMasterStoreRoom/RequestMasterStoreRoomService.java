package com.usdtl.ims.requestMaster.requestMasterStoreRoom;

import com.usdtl.ims.clients.responseRecord.ConfirmationEmailClient;
import com.usdtl.ims.common.exceptions.constants.Confirmation;
import com.usdtl.ims.common.exceptions.constants.Status;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.requestMaster.master.MasterRepository;
import com.usdtl.ims.requestMaster.master.MasterEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestMasterStoreRoomService {

    private RequestMasterStoreRoomRepository repository;
    private MasterRepository masterRepository;
    private ConfirmationEmailClient confirmationEmailClient;
    public List<RequestMasterStoreRoomEntity> createItems(List<RequestMasterStoreRoomEntity> requests) {
        List<RequestMasterStoreRoomEntity> newItems = new ArrayList<>();

        requests.forEach(request -> {
            MasterEntity masterItem = masterRepository
                    .findById(request.getMasterItem().getId()).orElseThrow(() -> new NotFoundException("Could not find the item"));
            RequestMasterStoreRoomEntity newItem = RequestMasterStoreRoomEntity.builder()
                    .quantity(request.getQuantity())
                    .department(request.getDepartment())
                    .user(request.getUser())
                    .detail(request.getDetail())
                    .customText(request.getCustomText())
                    .location(request.getLocation())
                    .status(Status.PENDING)
                    .timeRequested(new Date())
                    .timeRequested(new Date())
                    .confirmation(Confirmation.WAITING)
                    .build();
            newItem.setMasterItem(masterItem);
            repository.save(newItem);

            newItems.add(newItem);
        });
        return newItems;
    }
    public List<RequestMasterStoreRoomEntity> updateItems(List<RequestMasterStoreRoomEntity> requestItems) {
        List<RequestMasterStoreRoomEntity> requestMasterGeneralItems = new ArrayList<>();
        requestItems.forEach(item -> {
            RequestMasterStoreRoomEntity requestItem = repository.findById(item.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
            requestItem.setQuantity(item.getQuantity());
            requestItem.setDepartment(item.getDepartment());
            requestItem.setStatus(item.getStatus());
            requestItem.setLocation(item.getLocation());
            requestItem.setTimeUpdated(new Date());
            requestItem.setConfirmation(item.getConfirmation());
            requestItem.setUser(item.getUser());
            requestItem.setDetail(item.getDetail());
            requestItem.setCustomText(item.getCustomText());
            repository.save(requestItem);
            requestMasterGeneralItems.add(requestItem);
        });

        return requestMasterGeneralItems;
    }
    public RequestMasterStoreRoomEntity updateItem(Integer id, RequestMasterStoreRoomEntity requestItem) {
        RequestMasterStoreRoomEntity requestMasterItem = repository.findById(id).orElseThrow(() -> new NotFoundException("cannot find this item"));
        requestMasterItem.setQuantity(requestItem.getQuantity());
        requestMasterItem.setDepartment(requestItem.getDepartment());
        requestMasterItem.setStatus(requestItem.getStatus());
        requestMasterItem.setLocation(requestItem.getLocation());
        requestMasterItem.setTimeUpdated(new Date());
        requestMasterItem.setConfirmation(requestItem.getConfirmation());
        requestMasterItem.setUser(requestItem.getUser());
        requestMasterItem.setDetail(requestItem.getDetail());
        requestMasterItem.setCustomText(requestItem.getCustomText());
        repository.save(requestMasterItem);
        return requestMasterItem;
    }

    public List<RequestMasterStoreRoomEntity> confirmRequestItems(List<RequestMasterStoreRoomEntity> request) {
        List<RequestMasterStoreRoomEntity> requestClientItems = new ArrayList<>();

        request.forEach(item -> {
            RequestMasterStoreRoomEntity requestItem = repository.findById(item.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
            requestItem.setConfirmation(Confirmation.COMPLETE);

            MasterEntity masterItem = MasterEntity.builder()
                    .id(item.getMasterItem().getId())
                    .item(item.getMasterItem().getItem())
                    .manufacturer(item.getMasterItem().getManufacturer())
                    .partNumber(item.getMasterItem().getPartNumber())
                    .recentCN(item.getMasterItem().getRecentCN())
                    .recentVendor(item.getMasterItem().getRecentVendor())
                    .fisherCN(item.getMasterItem().getFisherCN())
                    .vwrCN(item.getMasterItem().getVwrCN())
                    .labSourceCN(item.getMasterItem().getLabSourceCN())
                    .otherCN(item.getMasterItem().getOtherCN())
                    .purchaseUnit(item.getMasterItem().getPurchaseUnit())
                    .unitPrice(item.getMasterItem().getUnitPrice())
                    .category(item.getMasterItem().getCategory())
                    .comment(item.getMasterItem().getComment())
                    .itemType(item.getMasterItem().getItemType())
                    .itemGroup(item.getMasterItem().getItemGroup())
                    .drugClass(item.getMasterItem().getDrugClass())
                    .build();

            RequestMasterStoreRoomEntity requestClientItem = RequestMasterStoreRoomEntity.builder()
                    .department(requestItem.getDepartment())
                    .status(requestItem.getStatus())
                    .location(requestItem.getLocation())
                    .timeUpdated(new Date())
                    .confirmation(requestItem.getConfirmation())
                    .user(requestItem.getUser())
                    .detail(requestItem.getDetail())
                    .customText(requestItem.getCustomText())
                    .masterItem(masterItem)
                    .build();

            repository.save(requestItem);
            requestClientItems.add(requestClientItem);
        });

//        confirmationEmailClient.sendConfirmationEmail(requestClientItems);

        return requestClientItems;
    }

    public Page<RequestMasterStoreRoomEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public Page<RequestMasterStoreRoomEntity> getCompleteItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByConfirmation(Confirmation.COMPLETE, pageRequest);
    }

    public Page<RequestMasterStoreRoomEntity> getPendingItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByConfirmation(Confirmation.WAITING, pageRequest);
    }
}
