package com.usdtl.ims.requestMaster.requestMasterOfficeSupply;

import com.usdtl.ims.clients.ConfirmationEmailClient;
import com.usdtl.ims.clients.MasterItemRequest;
import com.usdtl.ims.clients.RequestItemRequest;
import com.usdtl.ims.common.exceptions.constants.Confirmation;
import com.usdtl.ims.common.exceptions.constants.Status;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.master.MasterEntity;
import com.usdtl.ims.master.MasterRepository;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentRequest;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedDepartmentResponse;
import com.usdtl.ims.requestMaster.request.RequestMasterTransformedResponse;
import com.usdtl.ims.requestMaster.requestMasterGeneral.RequestMasterGeneralEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class RequestMasterOfficeSupplyService {

    private RequestMasterOfficeSupplyRepository repository;
    private final MasterRepository masterRepository;
    private ConfirmationEmailClient confirmationEmailClient;

    public List<RequestMasterTransformedDepartmentResponse> updateRequestItems(List<RequestMasterTransformedDepartmentRequest> requestItems) {
        List<RequestMasterTransformedDepartmentResponse> requestTransformedResponseItems = new ArrayList<>();
        requestItems.forEach(requestTransformedItem -> {
            RequestMasterOfficeSupplyEntity requestItem = repository.findById(requestTransformedItem.request_item_id()).orElseThrow(() -> new NotFoundException("cannot find this item"));
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

    public List<RequestMasterTransformedResponse> createRequestItem(List<RequestMasterTransformedDepartmentRequest> requestItems) {
        List<RequestMasterTransformedResponse> requestTransformedItemsResponse = new ArrayList<>();
        requestItems.forEach(requestItem -> {
            MasterEntity masterItem = masterRepository
                    .findById(requestItem.master_item_id()).orElseThrow(() -> new NotFoundException("Could not find the item"));
            RequestMasterOfficeSupplyEntity storeRoomRequest = RequestMasterOfficeSupplyEntity.builder()
                    .quantity(requestItem.quantity())
                    .department(requestItem.department())
                    .user(requestItem.user())
                    .detail(requestItem.detail())
                    .custom_text(requestItem.custom_text())
                    .location(requestItem.location())
                    .status(Status.PENDING)
                    .time_requested(new Date())
                    .time_updated(new Date())
                    .confirmation(Confirmation.WAITING)
                    .build();
            storeRoomRequest.setMasterItem(masterItem);
            repository.save(storeRoomRequest);
        });

        return requestTransformedItemsResponse;
    }

    public RequestMasterOfficeSupplyEntity updateRequestItem(Integer id, RequestMasterTransformedDepartmentRequest requestItem) {
        RequestMasterOfficeSupplyEntity requestMasterItem = repository.findById(requestItem.request_item_id()).orElseThrow(() -> new NotFoundException("cannot find this item"));
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
            RequestMasterOfficeSupplyEntity requestItem = repository.findById(item.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
            requestItem.setConfirmation(Confirmation.COMPLETE);

            MasterItemRequest masterItem = MasterItemRequest.builder()
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
                    .druClass(item.getMasterItem().getDrugClass())
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

    public Page<RequestMasterOfficeSupplyEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public Page<RequestMasterOfficeSupplyEntity> getCompleteItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByConfirmation(Confirmation.COMPLETE, pageRequest);
    }

    public Page<RequestMasterOfficeSupplyEntity> getPendingItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByConfirmation(Confirmation.WAITING, pageRequest);
    }
}
