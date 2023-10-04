package com.usdtl.inventory.masterDepartment.masterQcQa;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.masterProcessingLab.MasterProcessingLabEntity;
import com.usdtl.inventory.masterDepartment.masterProcessingLab.MasterProcessingLabOrderDetailEntity;
import com.usdtl.inventory.masterDepartment.masterProcessingLab.ProcessingLabEntity;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MasterQcQaService {
    private MasterQcQaRepository repository;
    private MasterQcQaOrderDetailRepository orderDetailRepository;

    public MasterQcQaEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterQcQaEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public Page<MasterQcQaEntity> filterItems(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<MasterQcQaEntity> sorItems(Integer page, String column, String direction) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        if(direction.isEmpty()) {
            Sort sort = Sort.by("id").ascending();
            pageRequest = PageRequest.of(page, 10, sort);
        }
        if(direction.equals("ASC")) {
            Sort sort = Sort.by(column).ascending();
            pageRequest = PageRequest.of(page, 10, sort);
        }
        if(direction.equals("DESC")) {
            Sort sort = Sort.by(column).descending();
            pageRequest = PageRequest.of(page, 10, sort);
        }
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }
    public MasterQcQaEntity create(MasterQcQaEntity request, Department department) {
        MasterQcQaEntity master = MasterQcQaEntity.builder()
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

    private MasterQcQaEntity getMasterExtractionsEntity(Department department, MasterQcQaEntity master) {
        if(department == Department.EXTRACTIONS) {
            QcQaEntity item = new QcQaEntity();
            List<QcQaEntity> items = new ArrayList<>();
            items.add(item);
            master.setDepartmentItems(items);
        }

        repository.save(master);
        return master;
    }

    public MasterQcQaEntity assign(Integer id, Department department) {
        MasterQcQaEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterExtractionsEntity(department, masterDepartment);
    }

    public String syncOrderDetails() {
        List<MasterQcQaEntity> masterDepartmentItems = repository.findByDepartmentItemsIsNotEmpty();
        masterDepartmentItems.forEach(masterDepartmentItem -> {
            if(masterDepartmentItem.getOrderDetail() == null) {
                Integer maximumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMaximumQuantity();
                Integer minimumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMinimumQuantity();
                Integer totalQuantity = masterDepartmentItem.getDepartmentItems().stream()
                        .peek(item -> {
                            if (item.getQuantity() == null)
                                item.setQuantity(0);

                        })
                        .mapToInt(QcQaEntity::getQuantity).sum();
                double totalPrice = masterDepartmentItem.getUnitPrice() * totalQuantity;
                Integer orderQuantity = 0;
                if (maximumQuantity == null || minimumQuantity == null) {
                    orderQuantity = null;
                } else if (maximumQuantity == 1 && minimumQuantity == 1 && totalQuantity < 1) {
                    orderQuantity = 1;
                } else if (totalQuantity < minimumQuantity) {
                    orderQuantity = maximumQuantity - totalQuantity;
                } else {
                    orderQuantity = 0;
                }

                MasterQcQaOrderDetailEntity newOrderDetail = MasterQcQaOrderDetailEntity.builder()
                        .totalPrice(totalPrice)
                        .totalQuantity(totalQuantity)
                        .orderQuantity(orderQuantity)
                        .build();
                masterDepartmentItem.setOrderDetail(newOrderDetail);
                repository.save(masterDepartmentItem);
            }
        });
        return "SUCCESS";
    }
}
