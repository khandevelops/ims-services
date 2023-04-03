package com.usdtl.ims.master;

import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MasterService {

    private MasterRepository repository;
    public MasterEntity createItem(MasterRequest request) {
        MasterEntity newMasterItem = MasterEntity.builder()
                .item(request.item())
                .manufacturer(request.manufacturer())
                .part_number(request.part_number())
                .recent_cn(request.recent_cn())
                .recent_vendor(request.recent_vendor())
                .fisher_cn(request.fisher_cn())
                .vwr_cn(request.vwr_cn())
                .lab_source_cn(request.lab_source_cn())
                .next_advance_cn(request.next_advance_cn())
                .purchase_unit(request.purchase_unit())
                .average_unit_price(request.average_unit_price())
                .category(request.category())
                .comments(request.comment())
                .type(request.type())
                .group(request.group())
                .build();

        repository.save(newMasterItem);
        return newMasterItem;
    };

    public MasterEntity updateItemById(Integer id, MasterRequest request) {
        MasterEntity masterItem = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        masterItem.setItem(request.item());
        masterItem.setManufacturer(request.manufacturer());
        masterItem.setPart_number(request.part_number());
        masterItem.setRecent_cn(request.recent_cn());
        masterItem.setRecent_vendor(request.recent_vendor());
        masterItem.setFisher_cn(request.fisher_cn());
        masterItem.setVwr_cn(request.vwr_cn());
        masterItem.setLab_source_cn(request.lab_source_cn());
        masterItem.setNext_advance_cn(request.next_advance_cn());
        masterItem.setPurchase_unit(request.purchase_unit());
        masterItem.setAverage_unit_price(request.average_unit_price());
        masterItem.setCategory(request.category());
        masterItem.setComments(request.comment());
        masterItem.setType(request.type());
        masterItem.setGroup(request.group());

        System.out.println("khan " + masterItem);
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

    public Page<MasterEntity> getItemsByPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest);

    }

    public MasterEntity getItemById(Integer id) throws NotFoundException {
        MasterEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }

    public Page<MasterEntity> getItemsFiltered(String item, Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAllByItemContainingIgnoreCase(item, pageRequest);
    }
}
