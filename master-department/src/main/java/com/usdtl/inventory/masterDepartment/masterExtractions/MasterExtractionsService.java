package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.masterTotal.MasterTotalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MasterExtractionsService {
    private MasterExtractionsRepository repository;
    private MasterTotalRepository masterTotalRepository;

    public MasterExtractionsEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterExtractionsEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        getMasterTotal(repository.findByDepartmentItemsIsNotEmpty(pageRequest));
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public void getMasterTotal(Page<MasterExtractionsEntity> masterDepartmentItems) {
        List<MasterTotalEntity> masterTotalEntityList= new ArrayList<>();
        masterDepartmentItems.forEach(masterDepartmentItem -> {
            Integer totalQuantity = masterDepartmentItem.getDepartmentItems().stream().mapToInt(ExtractionsEntity::getQuantity).sum();
            MasterTotalEntity masterTotal = MasterTotalEntity.builder()
                    .totalQuantity(totalQuantity)
                    .totalPrice(masterDepartmentItem.getUnitPrice() * totalQuantity)
                    .build();
            masterTotalEntityList.add(masterTotal);
        });
        masterTotalRepository.saveAll(masterTotalEntityList);
    }

    public Page<MasterExtractionsEntity> filterItems(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<MasterExtractionsEntity> sortItems(Integer page, String column, String direction) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        if(direction.isEmpty()) {
            Sort sort = Sort.by("id").ascending();
            pageRequest = PageRequest.of(page, 10, sort);
        }
        if(direction.equals("ASC")) {
            if(column.equals("totalQuantity")) {
                return repository.findAllByTotalQuantityOrderByAsc(pageRequest);
            } else if (column.equals("totalPrice")) {
                return repository.findAllByTotalPriceOrderByAsc(pageRequest);
            } else {
                Sort sort = Sort.by(column).ascending();
                pageRequest = PageRequest.of(page, 10, sort);
            }
        }
        if(direction.equals("DESC")) {
            if(column.equals("totalQuantity")) {
                return repository.findAllByTotalQuantityOrderByDesc(pageRequest);
            } else if (column.equals("totalPrice")) {
                return repository.findAllByTotalPriceOrderByDesc(pageRequest);
            } else {
                Sort sort = Sort.by(column).descending();
                pageRequest = PageRequest.of(page, 10, sort);
            }
        }
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public MasterExtractionsEntity create(MasterExtractionsEntity request, Department department) {
        MasterExtractionsEntity master = MasterExtractionsEntity.builder()
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


        return getMasterExtractionsEntity(department, master);
    }

    private MasterExtractionsEntity getMasterExtractionsEntity(Department department, MasterExtractionsEntity master) {
        if(department == Department.EXTRACTIONS) {
            ExtractionsEntity item = new ExtractionsEntity();
            List<ExtractionsEntity> items = new ArrayList<>();
            items.add(item);
            master.setDepartmentItems(items);
        }

        repository.save(master);
        return master;
    }

    public MasterExtractionsEntity assign(Integer id, Department department) {
        MasterExtractionsEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterExtractionsEntity(department, masterDepartment);
    }
}
