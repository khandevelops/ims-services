package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.ims.clients.responseRecord.MasterDepartmentResponse;
import com.usdtl.ims.common.exceptions.NotFoundException;
import com.usdtl.inventory.masterDepartment.entities.ExtractionsEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MasterExtractionsService {
    private MasterExtractionsRepository repository;

    public MasterExtractionsEntity getItemById(Integer id) throws NotFoundException {
        MasterExtractionsEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }

    public Page<MasterDepartmentResponse> getMasterDepartmentPageableItems(Integer page) {
        List<MasterDepartmentResponse> masterDepartmentResponseItems = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(page, 10);
        Long masterDepartmentItemCount = repository.count();

        List<MasterExtractionsEntity> masterExtractionsItems = (List<MasterExtractionsEntity>) repository.findAll(pageRequest);

        masterExtractionsItems.forEach(masterDepartmentItem -> {
            if(!masterDepartmentItem.getExtractionsItems().isEmpty()) {
                MasterDepartmentResponse masterDepartmentResponseItem = MasterDepartmentResponse.builder()
                        .department_item_id(masterDepartmentItem.getId())
                        .master_item_id(masterDepartmentItem.getId())
                        .master_item(masterDepartmentItem.getItem())
                        .purchase_unit(masterDepartmentItem.getPurchase_unit())
                        .part_number(masterDepartmentItem.getPart_number())
                        .recent_cn(masterDepartmentItem.getRecent_cn())
                        .total_quantity(getTotalQuantity(masterDepartmentItem.getExtractionsItems()))
                        .location(masterDepartmentItem.getExtractionsItems().get(0).getLocation())
                        .usage_level(masterDepartmentItem.getExtractionsItems().get(0).getUsage_level())
                        .min_quantity(masterDepartmentItem.getExtractionsItems().get(0).getMin_quantity())
                        .max_quantity(masterDepartmentItem.getExtractionsItems().get(0).getMax_quantity())
                        .order_quantity(getOrderQuantity(masterDepartmentItem.getExtractionsItems().get(0).getMax_quantity(), masterDepartmentItem.getExtractionsItems().get(0).getMin_quantity(), getTotalQuantity(masterDepartmentItem.getExtractionsItems())))
                        .unit_price(masterDepartmentItem.getAverage_unit_price())
                        .total_price(masterDepartmentItem.getAverage_unit_price() * getTotalQuantity(masterDepartmentItem.getExtractionsItems()))
                        .lot_number(masterDepartmentItem.getExtractionsItems().get(0).getLot_number())
                        .category(masterDepartmentItem.getCategory())
                        .comment(masterDepartmentItem.getComment())
                        .build();

                masterDepartmentResponseItems.add(masterDepartmentResponseItem);
            }
        });

        return new PageImpl<>(masterDepartmentResponseItems, pageRequest, masterDepartmentItemCount);
    }

    private Integer getOrderQuantity(Integer max_quantity, Integer min_quantity, Integer quantity) {
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

    private Integer getTotalQuantity(List<ExtractionsEntity> departmentItems) {
        return departmentItems.stream().mapToInt(ExtractionsEntity::getQuantity).sum();
    }

    public Page<MasterExtractionsEntity> getMasterDepartmentItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<MasterExtractionsEntity> masterDepartmentResponseItems = new ArrayList<>();
        long masterDepartmentItemCount = repository.count();

        List<MasterExtractionsEntity> masterDepartmentPageableItems = (List<MasterExtractionsEntity>) repository.findAll();

        masterDepartmentPageableItems.forEach(item -> {
            if(!item.getExtractionsItems().isEmpty()) {
                masterDepartmentResponseItems.add(item);
            }
        });

        List<MasterExtractionsEntity> pagedItems = masterDepartmentResponseItems.subList(page, page + 10);

        return new PageImpl<>(pagedItems, pageRequest, masterDepartmentItemCount);
    }
}
