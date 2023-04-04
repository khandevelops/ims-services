package com.usdtl.ims.storeRoomMaster;

import com.usdtl.ims.clients.StoreRoomMasterTransformedResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreRoomMasterService {

    private StoreRoomMasterRepository repository;

    public Page<StoreRoomMasterEntity> getStoreRoomItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public Page<StoreRoomMasterTransformedResponse> getStoreRoomMasterItemsByPage(Integer page) {
        List<StoreRoomMasterTransformedResponse> storeRoomMasterResponseItems = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(page, 10);
        Long storeRoomMasterItemCount = repository.count();
        List<StoreRoomMasterEntity> storeRoomMasterItems = repository.findAll(pageRequest).getContent();

        storeRoomMasterItems.forEach(storeRoomMasterItem -> {
            StoreRoomMasterTransformedResponse storeRoomMasterResponse = StoreRoomMasterTransformedResponse.builder()
                    .store_room_item_id(storeRoomMasterItem.getId())
                    .master_item_id(storeRoomMasterItem.getMasterItem().getId())
                    .item(storeRoomMasterItem.getMasterItem().getItem())
                    .purchase_unit(storeRoomMasterItem.getMasterItem().getPurchase_unit())
                    .part_number(storeRoomMasterItem.getMasterItem().getPart_number())
                    .recent_cn(storeRoomMasterItem.getMasterItem().getRecent_cn())
                    .location(storeRoomMasterItem.getLocation())
                    .total_quantity(storeRoomMasterItem.getQuantity())
                    .usage_level(storeRoomMasterItem.getUsage_level())
                    .min_quantity(storeRoomMasterItem.getMin_quantity())
                    .max_quantity(storeRoomMasterItem.getMax_quantity())
                    .order_quantity(getOrderQuantity(storeRoomMasterItem.getMax_quantity(), storeRoomMasterItem.getMin_quantity(), storeRoomMasterItem.getQuantity()))
                    .unit_price(storeRoomMasterItem.getMasterItem().getAverage_unit_price())
                    .total_price(storeRoomMasterItem.getMasterItem().getAverage_unit_price() * storeRoomMasterItem.getQuantity())
                    .comment(storeRoomMasterItem.getMasterItem().getComment())
                    .build();

            storeRoomMasterResponseItems.add(storeRoomMasterResponse);
        });

        return new PageImpl<>(storeRoomMasterResponseItems, pageRequest, storeRoomMasterItemCount);
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

//    public List<DepartmentTransformedResponse> getScheduledEmailItems() {
//        List<DepartmentTransformedResponse> transformedItems = new ArrayList<>();
//        List<ExtractionsMasterEntity> items = (List<ExtractionsMasterEntity>) repository.findAll();
//
//        items.forEach((item) -> {
//            Integer order_quantity = getOrderQuantity(item.getMax_quantity(), item.getMin_quantity(), item.getQuantity());
//            if(order_quantity > 0) {
//                DepartmentTransformedResponse response = DepartmentTransformedResponse.builder()
//                        .department_id(item.getId())
//                        .item_id(item.getMasterItem().getId())
//                        .item(item.getMasterItem().getItem())
//                        .purchase_unit(item.getMasterItem().getPurchase_unit())
//                        .part_number(item.getMasterItem().getPart_number())
//                        .recent_cn(item.getMasterItem().getRecent_cn())
//                        .recent_vendor(item.getMasterItem().getRecent_vendor())
//                        .location(item.getLocation())
//                        .total_quantity(getTotalQuantity(item.getMasterItem().getId()))
//                        .usage_level(item.getUsage_level())
//                        .min_quantity(item.getMin_quantity())
//                        .max_quantity(item.getMax_quantity())
//                        .order_quantity(order_quantity)
//                        .unit_price(item.getMasterItem().getAverage_unit_price())
//                        .total_price(item.getMasterItem().getAverage_unit_price() * getTotalQuantity(item.getMasterItem().getId()))
//                        .lot_number(item.getLot_number())
//                        .category(item.getMasterItem().getCategory())
//                        .comments(item.getMasterItem().getComments())
//                        .build();
//                transformedItems.add(response);
//            }
//        });
//        return transformedItems;
//    }
}
