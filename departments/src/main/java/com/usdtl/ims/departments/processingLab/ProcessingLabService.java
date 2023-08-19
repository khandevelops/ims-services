package com.usdtl.ims.departments.processingLab;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProcessingLabService {
    private ProcessingLabRepository repository;

    public ProcessingLabEntity createItem(ProcessingLabEntity request) {
        ProcessingLabEntity newItem = ProcessingLabEntity.builder()
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

    public ProcessingLabEntity updateItem(Integer id, ProcessingLabEntity request) {
        ProcessingLabEntity item = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
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

    public Page<ProcessingLabEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public ProcessingLabEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }
}

