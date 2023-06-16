package com.usdtl.ims.storeRoom;

import com.usdtl.ims.clients.StoreRoomResponse;
import com.usdtl.ims.clients.StoreRoomTransformedResponse;
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
public class StoreRoomService {

    private StoreRoomRepository repository;

    public Page<StoreRoomEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public StoreRoomEntity getItem(Integer id) throws NotFoundException {
        StoreRoomEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return item;
    }
    public StoreRoomEntity createItem(StoreRoomEntity request) {
        StoreRoomEntity newItem = StoreRoomEntity.builder()
                .location(request.getLocation())
                .quantity(request.getQuantity())
                .minimum_quantity(request.getMinimum_quantity())
                .maximum_quantity(request.getMaximum_quantity())
                .usage_level(request.getUsage_level())
                .lot_number(request.getLot_number())
                .expiration_date(request.getExpiration_date())
                .received_date(request.getReceived_date())
                .build();

        repository.save(newItem);
        return newItem;
    };

    public StoreRoomEntity updateItem(Integer id, StoreRoomEntity request) {
        StoreRoomEntity item = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        item.setLocation(request.getLocation());
        item.setQuantity(request.getQuantity());
        item.setMinimum_quantity(request.getMinimum_quantity());
        item.setMaximum_quantity(request.getMaximum_quantity());
        item.setUsage_level(request.getUsage_level());
        item.setLot_number(request.getLot_number());
        item.setExpiration_date(request.getExpiration_date());
        item.setReceived_date(request.getReceived_date());

        repository.save(item);

        return item;
    }

    public List<StoreRoomEntity> updateItems(List<StoreRoomEntity> request) {
        List<StoreRoomEntity> updateItems = new ArrayList<>();
        request.forEach(departmentItem -> {
            StoreRoomEntity item = repository.findById(departmentItem.getId()).orElseThrow(() ->
                    new NotFoundException("Item associated with id: " + departmentItem.getId() + " not found"));
            item.setLocation(departmentItem.getLocation());
            item.setQuantity(departmentItem.getQuantity());
            item.setMinimum_quantity(departmentItem.getMinimum_quantity());
            item.setMaximum_quantity(departmentItem.getMaximum_quantity());
            item.setUsage_level(departmentItem.getUsage_level());
            item.setLot_number(departmentItem.getLot_number());
            item.setExpiration_date(departmentItem.getExpiration_date());
            item.setReceived_date(departmentItem.getReceived_date());

            updateItems.add(item);
            repository.save(item);
        });

        return updateItems;
    }
    public void deleteItem(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);

    }
}
