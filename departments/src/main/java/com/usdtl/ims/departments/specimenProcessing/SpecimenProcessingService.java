package com.usdtl.ims.departments.specimenProcessing;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.departments.department.DepartmentRequest;
import com.usdtl.ims.departments.master.MasterRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SpecimenProcessingService {
    private SpecimenProcessingRepository repository;
    private MasterRepository masterRepository;
    public SpecimenProcessingEntity createItem(DepartmentRequest request) {
        SpecimenProcessingEntity newItem = SpecimenProcessingEntity.builder()
                .location(request.location())
                .quantity(request.quantity())
                .minimum_quantity(request.min_quantity())
                .maximum_quantity(request.max_quantity())
                .usage_level(request.usage_level())
                .lot_number(request.lot_number())
                .expiration_date(request.expiration_date())
                .received_date(request.received_date())
                .build();

        repository.save(newItem);
        return newItem;
    };

    public SpecimenProcessingEntity updateItemById(Integer id, DepartmentRequest request) {
        SpecimenProcessingEntity item = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        item.setLocation(request.location());
        item.setQuantity(request.quantity());
        item.setMinimum_quantity(request.min_quantity());
        item.setMaximum_quantity(request.max_quantity());
        item.setUsage_level(request.usage_level());
        item.setLot_number(request.lot_number());
        item.setExpiration_date(request.expiration_date());
        item.setReceived_date(request.received_date());

        repository.save(item);

        return item;
    }

    public List<SpecimenProcessingEntity> updateQuantity(List<DepartmentRequest> request) {
        List<SpecimenProcessingEntity> updateItems = new ArrayList<>();
        request.forEach(departmentItem -> {
            SpecimenProcessingEntity item = repository.findById(departmentItem.id()).orElseThrow(() -> new NotFoundException("Item associated with id: " + departmentItem.id() + " not found"));
            item.setLocation(departmentItem.location());
            item.setQuantity(departmentItem.quantity());
            item.setMinimum_quantity(departmentItem.min_quantity());
            item.setMaximum_quantity(departmentItem.max_quantity());
            item.setUsage_level(departmentItem.usage_level());
            item.setLot_number(departmentItem.lot_number());
            item.setExpiration_date(departmentItem.expiration_date());
            item.setReceived_date(departmentItem.received_date());

            updateItems.add(item);
            repository.save(item);
        });

        return updateItems;
    }

    public void deleteItemById(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);

    }

    public Page<SpecimenProcessingEntity> getItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public SpecimenProcessingEntity getItemById(Integer id) throws NotFoundException {
        SpecimenProcessingEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return item;
    }
}
