package com.usdtl.ims.department.extractions;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExtractionsService {
    private ExtractionsRepository repository;

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
                    departmentTransformedItem.getUnit_price() * departmentTransformedItem.getTotal_quantity()
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
        return 0;
    }
}

