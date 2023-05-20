package com.usdtl.ims.master;

import com.usdtl.ims.common.constants.Department;
import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.master.entities.*;
import com.usdtl.ims.master.repositories.*;
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
    private ReceivingRepository receivingRepository;
    private RdRepository rdRepository;
    private ScreeningRepository screeningRepository;
    private ShippingRepository shippingRepository;
    private QualityRepository qualityRepository;
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
                .next_advance_cn(request.masterItem().getNext_advance_cn())
                .purchase_unit(request.masterItem().getPurchase_unit())
                .average_unit_price(request.masterItem().getAverage_unit_price())
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

                }
                if(departmentName == Department.RECEIVING) {
                    ReceivingEntity receivingItem = new ReceivingEntity();
                    receivingItem.setMasterItem(newMasterItem);
                }
                if(departmentName == Department.RD) {
                    RdEntity rdItem = new RdEntity();
                    rdItem.setMasterItem(newMasterItem);
                }
                if(departmentName == Department.SCREENING) {
                    ScreeningEntity screeningItem = new ScreeningEntity();
                    screeningItem.setMasterItem(newMasterItem);
                }
                if(departmentName == Department.SHIPPING) {
                    ShippingEntity shippingItem = new ShippingEntity();
                    shippingItem.setMasterItem(newMasterItem);
                }
                if(departmentName == Department.QUALITY) {
                    QualityEntity qualityItem = new QualityEntity();
                    qualityItem.setMasterItem(newMasterItem);
                }
            });


        }

        repository.save(newMasterItem);
        return newMasterItem;




    };

    public MasterEntity updateItem(Integer id, MasterRequest request) {
        MasterEntity masterItem = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        masterItem.setItem(request.masterItem().getItem());
        masterItem.setManufacturer(request.masterItem().getManufacturer());
        masterItem.setPart_number(request.masterItem().getPart_number());
        masterItem.setRecent_cn(request.masterItem().getRecent_cn());
        masterItem.setRecent_vendor(request.masterItem().getRecent_vendor());
        masterItem.setFisher_cn(request.masterItem().getFisher_cn());
        masterItem.setVwr_cn(request.masterItem().getVwr_cn());
        masterItem.setLab_source_cn(request.masterItem().getLab_source_cn());
        masterItem.setNext_advance_cn(request.masterItem().getNext_advance_cn());
        masterItem.setPurchase_unit(request.masterItem().getPurchase_unit());
        masterItem.setAverage_unit_price(request.masterItem().getAverage_unit_price());
        masterItem.setCategory(request.masterItem().getCategory());
        masterItem.setComment(request.masterItem().getComment());
        masterItem.setType(request.masterItem().getType());
        masterItem.setGroup(request.masterItem().getGroup());
        masterItem.setExpiration_date(request.masterItem().getExpiration_date());
        masterItem.setReceived_date(request.masterItem().getReceived_date());

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

    public Page<MasterEntity> getItemsFiltered(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }
}
