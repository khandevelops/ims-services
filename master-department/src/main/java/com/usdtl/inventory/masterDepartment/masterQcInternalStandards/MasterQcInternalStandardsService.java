package com.usdtl.inventory.masterDepartment.masterQcInternalStandards;

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
public class MasterQcInternalStandardsService {
    private MasterQcInternalStandardsRepository repository;

    public MasterQcInternalStandardsEntity getItemById(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterQcInternalStandardsEntity> getMasterDepartmentItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public MasterQcInternalStandardsEntity create(MasterQcInternalStandardsEntity request, Department department) {
        MasterQcInternalStandardsEntity master = MasterQcInternalStandardsEntity.builder()
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


        return getMasterQcInternalStandardsEntity(department, master);
    }

    public MasterQcInternalStandardsEntity assign(Integer id, Department department) {
        MasterQcInternalStandardsEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterQcInternalStandardsEntity(department, masterDepartment);
    }

    private MasterQcInternalStandardsEntity getMasterQcInternalStandardsEntity(Department department, MasterQcInternalStandardsEntity masterDepartment) {
        if(department == Department.QC_INTERNAL_STANDARDS) {
            QcInternalStandardsEntity item = new QcInternalStandardsEntity();
            List<QcInternalStandardsEntity> items = new ArrayList<>();
            items.add(item);
            masterDepartment.setDepartmentItems(items);
        }

        repository.save(masterDepartment);
        return masterDepartment;
    }
}
