package com.usdtl.ims.departments.rd;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
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
    public RdEntity createItem(RdEntity request) {
        RdEntity newItem = RdEntity.builder()
                .location(request.getLocation())
                .quantity(request.getQuantity())
                .minimumQuantity(request.getMinimumQuantity())
                .maximumQuantity(request.getMaximumQuantity())
                .usageLevel(request.getUsageLevel())
                .lotNumber(request.getLotNumber())
                .expirationDate(request.getExpirationDate())
                .receivedDate(request.getReceivedDate())
                .build();

        repository.save(newItem);
        return newItem;
    };

    public RdEntity updateItem(Integer id, RdEntity request) {
        RdEntity item = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        item.setLocation(request.getLocation());
        item.setQuantity(request.getQuantity());
        item.setMinimumQuantity(request.getMinimumQuantity());
        item.setMaximumQuantity(request.getMaximumQuantity());
        item.setUsageLevel(request.getUsageLevel());
        item.setLotNumber(request.getLotNumber());
        item.setExpirationDate(request.getExpirationDate());
        item.setReceivedDate(request.getReceivedDate());

        repository.save(item);

        return item;
    }

    public void deleteItem(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);

    }

    public Page<RdEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public RdEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }
}
