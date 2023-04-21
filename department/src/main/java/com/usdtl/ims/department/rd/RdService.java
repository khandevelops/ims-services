package com.usdtl.ims.department.rd;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.ims.department.massSpec.MassSpecEntity;
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

    public Page<DepartmentMasterResponseTest> getDepartmentTransformedItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<DepartmentMasterResponseTest> departmentTransformedItems = repository.getDepartmentTransformedItems(pageRequest);

        departmentTransformedItems.getContent().forEach(departmentTransformedItem -> {
            departmentTransformedItem.setOrder_quantity(getOrderQuantity(
                    departmentTransformedItem.getMax_quantity(),
                    departmentTransformedItem.getMin_quantity(),
                    departmentTransformedItem.getTotal_quantity()
            ));
            departmentTransformedItem.setTotal_price(
                    departmentTransformedItem.getAverage_unit_price() * departmentTransformedItem.getTotal_quantity()
            );
        });

        return departmentTransformedItems;
    }

    private Integer getOrderQuantity(Integer max_quantity, Integer min_quantity, Long quantity) {
        if(max_quantity != null && min_quantity != null) {
            if(min_quantity == 1 && max_quantity == 1) {
                if(quantity < 1) {
                    return 1;
                }
            } else if(quantity <= min_quantity) {
                if(max_quantity - min_quantity < 0) {
                    throw new RuntimeException();
                }
                return max_quantity - min_quantity;
            }
        }
        return null;
    }
    public List<RdEntity> updateDepartmentItems(List<RdEntity> requestItems) {
        List<RdEntity> updateItems = new ArrayList<>();
        requestItems.forEach(departmentItem -> {
            RdEntity item = repository.findById(departmentItem.getId()).orElseThrow(() -> new NotFoundException("Item associated with id: " + departmentItem.getId() + " not found"));
            item.setLocation(departmentItem.getLocation());
            item.setQuantity(departmentItem.getQuantity());
            item.setMin_quantity(departmentItem.getMin_quantity());
            item.setMax_quantity(departmentItem.getMax_quantity());
            item.setUsage_level(departmentItem.getUsage_level());
            item.setLot_number(departmentItem.getLot_number());
            item.setExpiration_date(departmentItem.getExpiration_date());
            item.setReceived_date(departmentItem.getReceived_date());

            updateItems.add(item);
            repository.save(item);
        });

        return updateItems;
    }
}
