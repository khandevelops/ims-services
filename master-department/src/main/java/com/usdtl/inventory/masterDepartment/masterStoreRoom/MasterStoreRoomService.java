package com.usdtl.inventory.masterDepartment.masterStoreRoom;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MasterStoreRoomService {
    private MasterStoreRoomRepository repository;

    public MasterStoreRoomEntity getItemById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterStoreRoomEntity> getMasterDepartmentItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByStoreRoomItemsNotEmpty(pageRequest);
    }

    public MasterStoreRoomEntity create(MasterStoreRoomEntity request, Department department) {
        MasterStoreRoomEntity master = MasterStoreRoomEntity.builder()
                .item(request.getItem())
                .manufacturer(request.getManufacturer())
                .partNumber(request.getPartNumber())
                .recentCN(request.getRecentCN())
                .recentVendor(request.getRecentVendor())
                .fisherCN(request.getFisherCN())
                .vwrCN(request.getVwrCN())
                .labSourceCN(request.getLabSourceCN())
                .otherCN(request.getOtherCN())
                .purchaseUnit(request.getPurchaseUnit())
                .unitPrice(request.getUnitPrice())
                .category(request.getCategory())
                .comment(request.getComment())
                .itemType(request.getItemType())
                .itemGroup(request.getItemGroup())
                .drugClass(request.getDrugClass())
                .build();


        return getMasterStoreRoomEntity(department, master);
    }

    private MasterStoreRoomEntity getMasterStoreRoomEntity(Department department, MasterStoreRoomEntity master) {
        if(department == Department.STORE_ROOM) {
            StoreRoomEntity item = new StoreRoomEntity();
            List<StoreRoomEntity> items = new ArrayList<>();
            items.add(item);
            master.setStoreRoomItems(items);
        }

        repository.save(master);
        return master;
    }

    public MasterStoreRoomEntity assign(Integer id, Department department) {
        MasterStoreRoomEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterStoreRoomEntity(department, masterDepartment);
    }
}
