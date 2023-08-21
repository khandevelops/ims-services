package com.usdtl.ims.requestMaster.requestMasterOfficeSupply;

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
public class RequestMasterOfficeSupplyService {

    private RequestMasterOfficeSupplyRepository repository;
    private final MasterRepository masterRepository;
    private ConfirmationEmailClient confirmationEmailClient;
    public List<RequestMasterOfficeSupplyEntity> createItems(List<RequestMasterOfficeSupplyEntity> requests) {
        List<RequestMasterOfficeSupplyEntity> newRequestItems = new ArrayList<>();

        requests.forEach(requestItems -> {
            MasterEntity newMasterItem = masterRepository
                    .findById(requestItems.getMasterItem().getId()).orElseThrow(() -> new NotFoundException("Could not find the item"));
            RequestMasterOfficeSupplyEntity newOfficeSupplyRequestItem = RequestMasterOfficeSupplyEntity.builder()
                    .quantity(requestItems.getQuantity())
                    .department(requestItems.getDepartment())
                    .user(requestItems.getUser())
                    .detail(requestItems.getDetail())
                    .customText(requestItems.getCustomText())
                    .location(requestItems.getLocation())
                    .status(Status.PENDING)
                    .timeRequested(new Date())
                    .timeRequested(new Date())
                    .confirmation(Confirmation.WAITING)
                    .build();
            newOfficeSupplyRequestItem.setMasterItem(newMasterItem);
            repository.save(newOfficeSupplyRequestItem);

            newRequestItems.add(newOfficeSupplyRequestItem);
        });
        return newRequestItems;
    }
    public List<RequestMasterOfficeSupplyEntity> updateItems(List<RequestMasterOfficeSupplyEntity> requestItems) {
        List<RequestMasterOfficeSupplyEntity> newRequestItems = new ArrayList<>();
        requestItems.forEach(item -> {
            RequestMasterOfficeSupplyEntity existingRequestItem = repository.findById(item.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
            existingRequestItem.setQuantity(item.getQuantity());
            existingRequestItem.setDepartment(item.getDepartment());
            existingRequestItem.setStatus(item.getStatus());
            existingRequestItem.setLocation(item.getLocation());
            existingRequestItem.setTimeUpdated(new Date());
            existingRequestItem.setConfirmation(item.getConfirmation());
            existingRequestItem.setUser(item.getUser());
            existingRequestItem.setDetail(item.getDetail());
            existingRequestItem.setCustomText(item.getCustomText());
            repository.save(existingRequestItem);
            newRequestItems.add(existingRequestItem);
        });

        return newRequestItems;
    }
    public RequestMasterOfficeSupplyEntity updateItem(Integer id, RequestMasterOfficeSupplyEntity requestItem) {
        RequestMasterOfficeSupplyEntity existingRequestItem = repository.findById(id).orElseThrow(() -> new NotFoundException("cannot find this item"));
        existingRequestItem.setQuantity(requestItem.getQuantity());
        existingRequestItem.setDepartment(requestItem.getDepartment());
        existingRequestItem.setStatus(requestItem.getStatus());
        existingRequestItem.setLocation(requestItem.getLocation());
        existingRequestItem.setTimeUpdated(new Date());
        existingRequestItem.setConfirmation(requestItem.getConfirmation());
        existingRequestItem.setUser(requestItem.getUser());
        existingRequestItem.setDetail(requestItem.getDetail());
        existingRequestItem.setCustomText(requestItem.getCustomText());
        repository.save(existingRequestItem);
        return existingRequestItem;
    }

    public List<RequestMasterOfficeSupplyEntity> confirmItems(List<RequestMasterOfficeSupplyEntity> request) {
        List<RequestMasterOfficeSupplyEntity> newRequestItems = new ArrayList<>();

        request.forEach(item -> {
            RequestMasterOfficeSupplyEntity existingRequestItem = repository.findById(item.getId()).orElseThrow(() -> new NotFoundException("cannot find this item"));
            existingRequestItem.setConfirmation(Confirmation.COMPLETE);

            MasterEntity newMasterItem = MasterEntity.builder()
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

            RequestMasterOfficeSupplyEntity newRequestItem = RequestMasterOfficeSupplyEntity.builder()
                    .department(existingRequestItem.getDepartment())
                    .status(existingRequestItem.getStatus())
                    .location(existingRequestItem.getLocation())
                    .timeUpdated(new Date())
                    .confirmation(existingRequestItem.getConfirmation())
                    .user(existingRequestItem.getUser())
                    .detail(existingRequestItem.getDetail())
                    .customText(existingRequestItem.getCustomText())
                    .masterItem(newMasterItem)
                    .build();

            repository.save(newRequestItem);
            newRequestItems.add(newRequestItem);
        });

//        confirmationEmailClient.sendConfirmationEmail(newRequestItems);

        return newRequestItems;
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
