package com.usdtl.ims.department.shipping;

import com.usdtl.ims.clients.responseClass.DepartmentMasterResponseTest;
import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ShippingService {
    private ShippingRepository repository;

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


//    public ShippingEntity createItem(DepartmentRequest request) {
//        ShippingEntity newItem = ShippingEntity.builder()
//                .location(request.location())
//                .quantity(request.quantity())
//                .min_quantity(request.min_quantity())
//                .max_quantity(request.max_quantity())
//                .usage_level(request.usage_level())
//                .lot_number(request.lot_number())
//                .expiration_date(request.expiration_date())
//                .received_date(request.received_date())
//                .build();
//
//        repository.save(newItem);
//        return newItem;
//    };
//
//    public ShippingEntity updateItemById(Integer id, DepartmentRequest request) {
//        ShippingEntity item = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
//        item.setLocation(request.location());
//        item.setQuantity(request.quantity());
//        item.setMin_quantity(request.min_quantity());
//        item.setMax_quantity(request.max_quantity());
//        item.setUsage_level(request.usage_level());
//        item.setLot_number(request.lot_number());
//        item.setExpiration_date(request.expiration_date());
//        item.setReceived_date(request.received_date());
//
//        repository.save(item);
//
//        return item;
//    }
//
//    public List<ShippingEntity> updateQuantity(List<DepartmentRequest> request) {
//        List<ShippingEntity> updateItems = new ArrayList<>();
//        request.forEach(departmentItem -> {
//            ShippingEntity item = repository.findById(departmentItem.id()).orElseThrow(() -> new NotFoundException("Item associated with id: " + departmentItem.id() + " not found"));
//            item.setLocation(departmentItem.location());
//            item.setQuantity(departmentItem.quantity());
//            item.setMin_quantity(departmentItem.min_quantity());
//            item.setMax_quantity(departmentItem.max_quantity());
//            item.setUsage_level(departmentItem.usage_level());
//            item.setLot_number(departmentItem.lot_number());
//            item.setExpiration_date(departmentItem.expiration_date());
//            item.setReceived_date(departmentItem.received_date());
//
//            updateItems.add(item);
//            repository.save(item);
//        });
//
//        return updateItems;
//    }
//
//    public void deleteItemById(Integer id) {
//        boolean exists = repository.existsById(id);
//        if(!exists) {
//            throw new NotFoundException("Item associated with id: " + id + " not found");
//        }
//        repository.deleteById(id);
//
//    }
//
//    public Page<ShippingEntity> getItemsByPage(Integer page, Integer size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        return repository.findAll(pageRequest);
//    }
//
//    public ShippingEntity getItemById(Integer id) throws NotFoundException {
//        ShippingEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
//        return item;
//    }
}
