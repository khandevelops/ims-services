package com.usdtl.inventory.masterDepartment.masterShipping;

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
public class MasterShippingService {
    private MasterShippingRepository repository;

    public MasterShippingEntity getItemById(Integer id) throws NotFoundException {
        MasterShippingEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }

    public Page<MasterShippingEntity> getMasterDepartmentItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public MasterShippingEntity create(MasterShippingEntity request, Department department) {
        MasterShippingEntity master = MasterShippingEntity.builder()
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


        return getMasterShippingEntity(department, master);
    }

    public MasterShippingEntity assign(Integer id, Department department) {
        MasterShippingEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterShippingEntity(department, masterDepartment);
    }

    private MasterShippingEntity getMasterShippingEntity(Department department, MasterShippingEntity masterDepartment) {
        if(department == Department.SHIPPING) {
            ShippingEntity item = new ShippingEntity();
            List<ShippingEntity> items = new ArrayList<>();
            items.add(item);
            masterDepartment.setDepartmentItems(items);
        }

        repository.save(masterDepartment);
        return masterDepartment;
    }
}
