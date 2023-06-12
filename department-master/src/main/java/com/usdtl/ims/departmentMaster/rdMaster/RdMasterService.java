package com.usdtl.ims.departmentMaster.rdMaster;


import com.usdtl.ims.clients.DepartmentMasterResponse;
import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.DepartmentResponse;
import com.usdtl.ims.clients.MasterDepartmentResponse;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RdMasterService {
    private RdMasterRepository repository;
    private MasterDepartmentClient client;

    public Page<DepartmentMasterResponse> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<DepartmentMasterResponse> departmentMasterItems = repository.getDepartmentMasterItems(pageRequest);
        return departmentMasterItems;
    }
    public RdMasterEntity createItem(RdMasterRequest request) {
        RdMasterEntity newItem = RdMasterEntity.builder()
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

    public Page<RdMasterEntity> getExtractionsExperienceItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public void deleteItemById(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);

    }

    public Page<DepartmentMasterResponse> getExperienceItemsByPage(Integer page) {
        List<DepartmentMasterResponse> departmentMasterItems = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(page, 10);
        Long rdItemsCount = repository.count();
        List<RdMasterEntity> departmentPageableItems = repository.findAll(pageRequest).getContent();
        departmentPageableItems.forEach((departmentItems) -> {
            DepartmentMasterResponse experienceResponse = DepartmentMasterResponse.builder()
                    .department_id(departmentItems.getId())
                    .item(departmentItems.getMasterItem().getItem())
                    .purchase_unit(departmentItems.getMasterItem().getPurchase_unit())
                    .part_number(departmentItems.getMasterItem().getPart_number())
                    .recent_cn(departmentItems.getMasterItem().getRecent_cn())
                    .location(departmentItems.getLocation())
                    .total_quantity(getTotalQuantity(departmentItems.getMasterItem().getId()))
                    .usage_level(departmentItems.getUsage_level())
                    .min_quantity(departmentItems.getMin_quantity())
                    .max_quantity(departmentItems.getMax_quantity())
                    .order_quantity(getOrderQuantity(departmentItems.getMax_quantity(), departmentItems.getMin_quantity(), departmentItems.getQuantity()))
                    .unit_price(departmentItems.getMasterItem().getAverage_unit_price())
                    .total_price(departmentItems.getMasterItem().getAverage_unit_price() * getTotalQuantity(departmentItems.getMasterItem().getId()))
                    .lot_number(departmentItems.getLot_number())
                    .category(departmentItems.getMasterItem().getCategory())
                    .comment(departmentItems.getMasterItem().getComment())
                    .build();
            departmentMasterItems.add(experienceResponse);
        });

        return new PageImpl<>(departmentMasterItems, pageRequest, rdItemsCount);
    }

    public RdMasterEntity getItemById(Integer id) throws NotFoundException {
        RdMasterEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return item;
    }

//    public void updateOrderQuantity() {
//        List<RdExperienceEntity> items = (List<RdExperienceEntity>) repository.findAll();
//        items.forEach((item) -> {
//            if(item.getMax_quantity() != null && item.getMin_quantity() != null) {
//                if(item.getMin_quantity() == 1 && item.getMax_quantity() == 1) {
//                    if(item.getQuantity() < 1) {
//                        item.setOrder_quantity(1);
//                    }
//                } else if(item.getQuantity() <= item.getMin_quantity()) {
//                    item.setOrder_quantity(item.getMax_quantity() - item.getQuantity());
//                } else {
//                    item.setOrder_quantity(0);
//                }
//            }
//            repository.save(item);
//        });
//    }

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

    private Integer getTotalQuantity(Integer id) {
        MasterDepartmentResponse response = client.getMasterRdItemById(id);
        return response.departmentItems().stream().mapToInt(DepartmentResponse::quantity).sum();
    }
}