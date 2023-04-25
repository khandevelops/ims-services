package com.usdtl.ims.downloads.massSpecMaster;

import com.usdtl.ims.clients.DepartmentMasterResponse;
import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.DepartmentResponse;
import com.usdtl.ims.clients.MasterDepartmentResponse;
import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MassSpecMasterService {
    private MassSpecMasterRepository repository;
    private MasterDepartmentClient client;

    public Page<DepartmentMasterResponse> getDepartmentMasterItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<DepartmentMasterResponse> departmentMasterItems = repository.getDepartmentMasterItems(pageRequest);
        return departmentMasterItems;
    }
    public MassSpecMasterEntity createItem(MassSpecMasterRequest request) {
        MassSpecMasterEntity newItem = MassSpecMasterEntity.builder()
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

    public Page<MassSpecMasterEntity> getExtractionsExperienceItemsByPage(Integer page) {
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
        Long massSpecItemsCount = repository.count();
        List<MassSpecMasterEntity> extractionsItems = repository.findAll(pageRequest).getContent();
        extractionsItems.forEach((extractionsItem) -> {
            DepartmentMasterResponse experienceResponse = DepartmentMasterResponse.builder()
                    .department_id(extractionsItem.getId())
                    .item_id(extractionsItem.getMasterItem().getId())
                    .item(extractionsItem.getMasterItem().getItem())
                    .purchase_unit(extractionsItem.getMasterItem().getPurchase_unit())
                    .part_number(extractionsItem.getMasterItem().getPart_number())
                    .recent_cn(extractionsItem.getMasterItem().getRecent_cn())
                    .location(extractionsItem.getLocation())
                    .total_quantity(getTotalQuantity(extractionsItem.getMasterItem().getId()))
                    .usage_level(extractionsItem.getUsage_level())
                    .min_quantity(extractionsItem.getMin_quantity())
                    .max_quantity(extractionsItem.getMax_quantity())
                    .order_quantity(getOrderQuantity(extractionsItem.getMax_quantity(), extractionsItem.getMin_quantity(), extractionsItem.getQuantity()))
                    .unit_price(extractionsItem.getMasterItem().getAverage_unit_price())
                    .total_price(extractionsItem.getMasterItem().getAverage_unit_price() * getTotalQuantity(extractionsItem.getMasterItem().getId()))
                    .lot_number(extractionsItem.getLot_number())
                    .category(extractionsItem.getMasterItem().getCategory())
                    .comment(extractionsItem.getMasterItem().getComment())
                    .build();
            departmentMasterItems.add(experienceResponse);
        });

        return new PageImpl<>(departmentMasterItems, pageRequest, massSpecItemsCount);
    }

    public MassSpecMasterEntity getItemById(Integer id) throws NotFoundException {
        MassSpecMasterEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return item;
    }

//    public void updateOrderQuantity() {
//        List<MassSpecExperienceEntity> items = (List<MassSpecExperienceEntity>) repository.findAll();
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
        MasterDepartmentResponse response = client.getMasterMassSpecItemById(id);
        return response.massSpecItems().stream().mapToInt(DepartmentResponse::quantity).sum();
    }
}