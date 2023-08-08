package com.usdtl.inventory.masterDepartment.masterSpecimenProcessing;

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
public class MasterSpecimenProcessingService {
    private MasterSpecimenProcessingRepository repository;

    public MasterSpecimenProcessingEntity getItemById(Integer id) throws NotFoundException {
        MasterSpecimenProcessingEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }

    public Page<MasterSpecimenProcessingEntity> getMasterDepartmentItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public MasterSpecimenProcessingEntity create(MasterSpecimenProcessingEntity request, Department department) {
        MasterSpecimenProcessingEntity master = MasterSpecimenProcessingEntity.builder()
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


        return getMasterSpecimenProcessingEntity(department, master);
    }

    private MasterSpecimenProcessingEntity getMasterSpecimenProcessingEntity(Department department, MasterSpecimenProcessingEntity master) {
        if(department == Department.SPECIMEN_PROCESSING) {
            SpecimenProcessingEntity item = new SpecimenProcessingEntity();
            List<SpecimenProcessingEntity> items = new ArrayList<>();
            items.add(item);
            master.setDepartmentItems(items);
        }

        repository.save(master);
        return master;
    }

    public MasterSpecimenProcessingEntity assign(Integer id, Department department) {
        MasterSpecimenProcessingEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterSpecimenProcessingEntity(department, masterDepartment);
    }
}
