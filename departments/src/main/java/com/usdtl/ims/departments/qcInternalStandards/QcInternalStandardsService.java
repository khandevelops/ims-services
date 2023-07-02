package com.usdtl.ims.departments.qcInternalStandards;

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
public class QcInternalStandardsService {
    private QcInternalStandardsRepository repository;
    private MasterRepository masterRepository;
    public QcInternalStandardsEntity createItem(DepartmentRequest request) {
        QcInternalStandardsEntity newItem = QcInternalStandardsEntity.builder()
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

    public QcInternalStandardsEntity updateItemById(Integer id, DepartmentRequest request) {
        QcInternalStandardsEntity item = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
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

    public List<QcInternalStandardsEntity> updateQuantity(List<DepartmentRequest> request) {
        List<QcInternalStandardsEntity> updateItems = new ArrayList<>();
        request.forEach(departmentItem -> {
            QcInternalStandardsEntity item = repository.findById(departmentItem.id()).orElseThrow(() -> new NotFoundException("Item associated with id: " + departmentItem.id() + " not found"));
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

    public Page<QcInternalStandardsEntity> getItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public QcInternalStandardsEntity getItemById(Integer id) throws NotFoundException {
        QcInternalStandardsEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return item;
    }
}
