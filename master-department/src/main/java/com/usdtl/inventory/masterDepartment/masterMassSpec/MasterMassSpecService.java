package com.usdtl.inventory.masterDepartment.masterMassSpec;

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
public class MasterMassSpecService {
    private MasterMassSpecRepository repository;

    public MasterMassSpecEntity getItemById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterMassSpecEntity> getMasterDepartmentItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public MasterMassSpecEntity create(MasterMassSpecEntity request, Department department) {
        MasterMassSpecEntity master = MasterMassSpecEntity.builder()
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


        return getMasterMassSpecEntity(department, master);
    }

    public MasterMassSpecEntity assign(Integer id, Department department) {
        MasterMassSpecEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterMassSpecEntity(department, masterDepartment);
    }

    private MasterMassSpecEntity getMasterMassSpecEntity(Department department, MasterMassSpecEntity masterDepartment) {
        if(department == Department.MASS_SPEC) {
            MassSpecEntity item = new MassSpecEntity();
            List<MassSpecEntity> items = new ArrayList<>();
            items.add(item);
            masterDepartment.setDepartmentItems(items);
        }

        repository.save(masterDepartment);
        return masterDepartment;
    }

}
