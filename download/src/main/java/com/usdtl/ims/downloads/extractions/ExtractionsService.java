package com.usdtl.ims.downloads.extractions;

import com.usdtl.ims.clients.DepartmentResponse;
import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.MasterDepartmentResponse;
import com.usdtl.ims.clients.responseClass.DepartmentDownloadResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExtractionsService {
    private ExtractionsRepository repository;
    private MasterDepartmentClient client;

    public List<DepartmentDownloadResponse> getDepartmentTransformedItems() {
        List<DepartmentDownloadResponse> departmentTransformedItems = repository.getDepartmentTransformedItems();

        departmentTransformedItems.forEach(departmentTransformedItem -> {
            departmentTransformedItem.setTotal_price(
                    departmentTransformedItem.getAverage_unit_price() * departmentTransformedItem.getQuantity()
            );
            departmentTransformedItem.setTotal_quantity(getTotalQuantity(departmentTransformedItem.getMaster_item_id()));
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

    private Long getTotalQuantity(Integer id) {
        MasterDepartmentResponse response = client.getMasterExtractionsItemById(id);
        return (long) response.departmentItems().stream().mapToInt(DepartmentResponse::quantity).sum();
    }
}