package com.usdtl.ims.master;

import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.master.entities.*;
import com.usdtl.ims.master.repositories.*;
import com.usdtl.ims.master.requests.AssignRequest;
import com.usdtl.ims.master.responses.AssignResponse;
import com.usdtl.ims.master.responses.DeleteResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterService {

    private MasterRepository repository;
    private ExtractionsRepository extractionsRepository;
    private MassSpecRepository massSpecRepository;
    private SpecimenProcessingRepository receivingRepository;
    private RdRepository rdRepository;
    private ScreeningRepository screeningRepository;
    private ShippingRepository shippingRepository;
    private QualityRepository qualityRepository;
    private RequestGeneralRepository requestGeneralRepository;
    private RequestOfficeSupplyRepository requestOfficeSupplyRepository;
    private RequestStoreRoomRepository requestStoreRoomRepository;
    public MasterEntity createItem(MasterRequest request) {
        MasterEntity newMasterItem = MasterEntity.builder()
                .item(request.masterItem().getItem())
                .manufacturer(request.masterItem().getManufacturer())
                .partNumber(request.masterItem().getPartNumber())
                .recentCN(request.masterItem().getRecentCN())
                .recentVendor(request.masterItem().getRecentVendor())
                .fisherCN(request.masterItem().getFisherCN())
                .vwrCN(request.masterItem().getVwrCN())
                .labSourceCN(request.masterItem().getLabSourceCN())
                .otherCN(request.masterItem().getOtherCN())
                .purchaseUnit(request.masterItem().getPurchaseUnit())
                .unitPrice(request.masterItem().getUnitPrice())
                .category(request.masterItem().getCategory())
                .comment(request.masterItem().getComment())
                .itemType(request.masterItem().getItemType())
                .itemGroup(request.masterItem().getItemGroup())
                .drugClass(request.masterItem().getDrugClass())
                .build();

        repository.save(newMasterItem);

        if(!request.departments().isEmpty()) {
            request.departments().forEach(departmentName -> {
                if(departmentName == Department.EXTRACTIONS) {
                    ExtractionsEntity extractionsItem = new ExtractionsEntity();
                    extractionsItem.setMasterItem(newMasterItem);
                    extractionsRepository.save(extractionsItem);
                }
                if(departmentName == Department.MASS_SPEC) {
                    MassSpecEntity massSpecItem = new MassSpecEntity();
                    massSpecItem.setMasterItem(newMasterItem);
                    massSpecRepository.save(massSpecItem);
                }
                if(departmentName == Department.RD) {
                    RdEntity rdItem = new RdEntity();
                    rdItem.setMasterItem(newMasterItem);
                    rdRepository.save(rdItem);
                }
                if(departmentName == Department.SCREENING) {
                    ScreeningEntity screeningItem = new ScreeningEntity();
                    screeningItem.setMasterItem(newMasterItem);
                    screeningRepository.save(screeningItem);
                }
                if(departmentName == Department.SHIPPING) {
                    ShippingEntity shippingItem = new ShippingEntity();
                    shippingItem.setMasterItem(newMasterItem);
                    shippingRepository.save(shippingItem);
                }
                if(departmentName == Department.QUALITY) {
                    QualityEntity qualityItem = new QualityEntity();
                    qualityItem.setMasterItem(newMasterItem);
                    qualityRepository.save(qualityItem);
                }
                if(departmentName == Department.GENERAL) {
                    RequestGeneralEntity requestGeneralEntity = new RequestGeneralEntity();
                    requestGeneralEntity.setMasterItem(newMasterItem);
                    requestGeneralRepository.save(requestGeneralEntity);
                }
                if(departmentName == Department.STORE_ROOM) {
                    RequestStoreRoomEntity requestStoreRoomEntity = new RequestStoreRoomEntity();
                    requestStoreRoomEntity.setMasterItem(newMasterItem);
                    requestStoreRoomRepository.save(requestStoreRoomEntity);
                }
                if(departmentName == Department.OFFICE_SUPPLY) {
                    RequestOfficeSupplyEntity requestOfficeSupplyEntity = new RequestOfficeSupplyEntity();
                    requestOfficeSupplyEntity.setMasterItem(newMasterItem);
                    requestOfficeSupplyRepository.save(requestOfficeSupplyEntity);
                }
            });
        }
        return newMasterItem;
    };

    public AssignResponse assignItem(Integer masterItemId, Department departmentName) {
        MasterEntity masterItem = repository.findById(masterItemId).orElseThrow();
        if(departmentName == Department.EXTRACTIONS) {
            ExtractionsEntity extractionsItem = new ExtractionsEntity();
            extractionsItem.setMasterItem(masterItem);
            extractionsRepository.save(extractionsItem);
        }
        if(departmentName == Department.MASS_SPEC) {
            MassSpecEntity massSpecItem = new MassSpecEntity();
            massSpecItem.setMasterItem(masterItem);
            massSpecRepository.save(massSpecItem);
        }
        if(departmentName == Department.RD) {
            RdEntity rdItem = new RdEntity();
            rdItem.setMasterItem(masterItem);
            rdRepository.save(rdItem);
        }
        if(departmentName == Department.SCREENING) {
            ScreeningEntity screeningItem = new ScreeningEntity();
            screeningItem.setMasterItem(masterItem);
            screeningRepository.save(screeningItem);
        }
        if(departmentName == Department.SHIPPING) {
            ShippingEntity shippingItem = new ShippingEntity();
            shippingItem.setMasterItem(masterItem);
            shippingRepository.save(shippingItem);
        }
        if(departmentName == Department.QUALITY) {
            QualityEntity qualityItem = new QualityEntity();
            qualityItem.setMasterItem(masterItem);
            qualityRepository.save(qualityItem);
        }
        if(departmentName == Department.GENERAL) {
            RequestGeneralEntity requestGeneralEntity = new RequestGeneralEntity();
            requestGeneralEntity.setMasterItem(masterItem);
            requestGeneralRepository.save(requestGeneralEntity);
        }
        if(departmentName == Department.STORE_ROOM) {
            RequestStoreRoomEntity requestStoreRoomEntity = new RequestStoreRoomEntity();
            requestStoreRoomEntity.setMasterItem(masterItem);
            requestStoreRoomRepository.save(requestStoreRoomEntity);
        }
        if(departmentName == Department.OFFICE_SUPPLY) {
            RequestOfficeSupplyEntity requestOfficeSupplyEntity = new RequestOfficeSupplyEntity();
            requestOfficeSupplyEntity.setMasterItem(masterItem);
            requestOfficeSupplyRepository.save(requestOfficeSupplyEntity);
        }
        return new AssignResponse(departmentName, masterItem);
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
