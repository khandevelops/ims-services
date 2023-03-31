package com.usdtl.ims.departments.rd;

import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.departments.department.DepartmentRequest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RdService {
    private RdRepository repository;
    public RdEntity createItem(DepartmentRequest request) {
        RdEntity newItem = RdEntity.builder()
                .location(request.location())
                .quantity(request.quantity())
                .min_quantity(request.min_quantity())
                .max_quantity(request.max_quantity())
                .usage_level(request.usage_level())
                .lot_number(request.lot_number())
                .expiration_date(request.expiration_date())
                .received_date(request.received_date())
                .build();

        repository.save(newItem);
        return newItem;
    };

    public RdEntity updateItemById(Integer id, DepartmentRequest request) {
        RdEntity item = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        item.setLocation(request.location());
        item.setQuantity(request.quantity());
        item.setMin_quantity(request.min_quantity());
        item.setMax_quantity(request.max_quantity());
        item.setUsage_level(request.usage_level());
        item.setLot_number(request.lot_number());
        item.setExpiration_date(request.expiration_date());
        item.setReceived_date(request.received_date());

        repository.save(item);

        return item;
    }

    public List<RdEntity> updateQuantity(List<DepartmentRequest> request) {
        List<RdEntity> updateItems = new ArrayList<>();
        request.forEach(departmentItem -> {
            RdEntity item = repository.findById(departmentItem.id()).orElseThrow(() -> new NotFoundException("Item associated with id: " + departmentItem.id() + " not found"));
            item.setLocation(departmentItem.location());
            item.setQuantity(departmentItem.quantity());
            item.setMin_quantity(departmentItem.min_quantity());
            item.setMax_quantity(departmentItem.max_quantity());
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

    public Page<RdEntity> getItemsByPage(Integer page, Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repository.findAll(pageRequest);
    }

    public RdEntity getItemById(Integer id) throws NotFoundException {
        RdEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return item;
    }
}
