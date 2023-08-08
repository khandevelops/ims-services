package com.usdtl.ims.master;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.master.responses.DeleteResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterService {

    private MasterRepository repository;
    public MasterEntity createItem(MasterEntity request) {
        MasterEntity master = MasterEntity.builder()
                .item(request.getItem())
                .manufacturer(request.getManufacturer())
                .partNumber(request.getPartNumber())
                .recentCN(request.getRecentCN())
                .recentVendor(request.getRecentVendor())
                .fisherCN(request.getFisherCN())
                .vwrCN(request.getVwrCN())
                .labSourceCN(request.getLabSourceCN())
                .otherCN(request.getOtherCN())
                .purchaseUnit(request.getPurchaseUnit())
                .unitPrice(request.getUnitPrice())
                .category(request.getCategory())
                .comment(request.getComment())
                .itemType(request.getItemType())
                .itemGroup(request.getItemGroup())
                .drugClass(request.getDrugClass())
                .build();

        repository.save(master);
        return master;
    }

    public MasterEntity updateItem(Integer id, MasterEntity request) {
        MasterEntity masterItem = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        masterItem.setItem(request.getItem());
        masterItem.setManufacturer(request.getManufacturer());
        masterItem.setPartNumber(request.getPartNumber());
        masterItem.setRecentCN(request.getRecentCN());
        masterItem.setRecentVendor(request.getRecentVendor());
        masterItem.setFisherCN(request.getFisherCN());
        masterItem.setVwrCN(request.getVwrCN());
        masterItem.setLabSourceCN(request.getLabSourceCN());
        masterItem.setOtherCN(request.getOtherCN());
        masterItem.setPurchaseUnit(request.getPurchaseUnit());
        masterItem.setUnitPrice(request.getUnitPrice());
        masterItem.setCategory(request.getCategory());
        masterItem.setComment(request.getComment());
        masterItem.setItemType(request.getItemType());
        masterItem.setItemGroup(request.getItemGroup());
        masterItem.setDrugClass(request.getDrugClass());

        repository.save(masterItem);

        return masterItem;
    }

    public ResponseEntity<DeleteResponse> deleteItemById(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);
        DeleteResponse deleteResponse = new DeleteResponse("SUCCESS", id);
        return ResponseEntity.status(HttpStatus.OK).body(deleteResponse);
    }
    public Page<MasterEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10, Sort.by("id").ascending());
        return repository.findAll(pageRequest);
    }

    public Page<MasterEntity> sorItems(Integer page, String column, String direction) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        if(direction.isEmpty()) {
            Sort sort = Sort.by("id").ascending();
            pageRequest = PageRequest.of(page, 10, sort);
        }
        if(direction.equals("ASC")) {
            Sort sort = Sort.by(column).ascending();
            pageRequest = PageRequest.of(page, 10, sort);
        }
        if(direction.equals("DESC")) {
            Sort sort = Sort.by(column).descending();
            pageRequest = PageRequest.of(page, 10, sort);
        }
        return repository.findAll(pageRequest);
    }

    public MasterEntity getItemById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterEntity> getItemsByKeyword(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }
}
