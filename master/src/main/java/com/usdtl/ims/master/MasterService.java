package com.usdtl.ims.master;

import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.master.entities.*;
import com.usdtl.ims.master.repositories.*;
import com.usdtl.ims.master.requests.AssignRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
                .part_number(request.masterItem().getPart_number())
                .recent_cn(request.masterItem().getRecent_cn())
                .recent_vendor(request.masterItem().getRecent_vendor())
                .fisher_cn(request.masterItem().getFisher_cn())
                .vwr_cn(request.masterItem().getVwr_cn())
                .lab_source_cn(request.masterItem().getLab_source_cn())
                .other_cn(request.masterItem().getOther_cn())
                .purchase_unit(request.masterItem().getPurchase_unit())
                .unit_price(request.masterItem().getUnit_price())
                .category(request.masterItem().getCategory())
                .comment(request.masterItem().getComment())
                .type(request.masterItem().getType())
                .group(request.masterItem().getGroup())
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

    public MasterEntity assignItem(AssignRequest assignRequest) {
        Integer masterItemId = assignRequest.masterItemId();
        Department departmentName = assignRequest.department();

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
        return masterItem;
    }

    public MasterEntity updateItem(Integer id, MasterEntity request) {
        MasterEntity masterItem = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        masterItem.setItem(request.getItem());
        masterItem.setManufacturer(request.getManufacturer());
        masterItem.setPart_number(request.getPart_number());
        masterItem.setRecent_cn(request.getRecent_cn());
        masterItem.setRecent_vendor(request.getRecent_vendor());
        masterItem.setFisher_cn(request.getFisher_cn());
        masterItem.setVwr_cn(request.getVwr_cn());
        masterItem.setLab_source_cn(request.getLab_source_cn());
        masterItem.setOther_cn(request.getOther_cn());
        masterItem.setPurchase_unit(request.getPurchase_unit());
        masterItem.setUnit_price(request.getUnit_price());
        masterItem.setCategory(request.getCategory());
        masterItem.setComment(request.getComment());
        masterItem.setType(request.getType());
        masterItem.setGroup(request.getGroup());

        repository.save(masterItem);

        return masterItem;
    }

    public void deleteItemById(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);

    }

    public Page<MasterEntity> getItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);

    }

    public MasterEntity getItemById(Integer id) throws NotFoundException {
        MasterEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }

    public Page<MasterEntity> getItemsByKeyword(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }
}
