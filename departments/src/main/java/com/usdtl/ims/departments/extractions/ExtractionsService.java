package com.usdtl.ims.departments.extractions;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExtractionsService {
    private ExtractionsRepository repository;
    public ExtractionsEntity createItem(ExtractionsEntity request) {
        ExtractionsEntity newItem = ExtractionsEntity.builder()
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

    public ExtractionsEntity updateItem(Integer id, ExtractionsEntity request) {
        ExtractionsEntity item = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
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

    public Page<ExtractionsEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }
    public ExtractionsEntity getItem(Integer id) throws NotFoundException {
        return  repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }
    public ResponseEntity<String> deleteItem(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("SUCCESS");
    }
}

