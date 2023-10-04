package com.usdtl.inventory.masterDepartment.masterProcessingLab;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsOrderDetailEntity;
import com.usdtl.inventory.masterDepartment.masterMassSpec.MasterMassSpecOrderDetailRepository;
import lombok.AllArgsConstructor;
import org.apache.commons.math3.util.Precision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MasterProcessingLabService {
    private MasterProcessingLabRepository repository;
    private MasterProcessingLabOrderDetailRepository orderDetailRepository;

    public MasterProcessingLabEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterProcessingLabEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public Page<MasterProcessingLabEntity> filterItems(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<MasterProcessingLabEntity> sorItems(Integer page, String column, String direction) {
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

    public MasterProcessingLabEntity create(MasterProcessingLabEntity request, Department department) {
        MasterProcessingLabEntity master = MasterProcessingLabEntity.builder()
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


        return getMasterDepartmentEntity(department, master);
    }

    private MasterProcessingLabEntity getMasterDepartmentEntity(Department department, MasterProcessingLabEntity master) {
        if(department == Department.PROCESSING_LAB) {
            ProcessingLabEntity item = new ProcessingLabEntity();
            List<ProcessingLabEntity> items = new ArrayList<>();
            items.add(item);
            master.setDepartmentItems(items);
        }

        repository.save(master);
        return master;
    }

    public MasterProcessingLabEntity assign(Integer id, Department department) {
        MasterProcessingLabEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterDepartmentEntity(department, masterDepartment);
    }

    public String syncOrderDetails() {
        List<MasterProcessingLabEntity> masterDepartmentItems = repository.findByDepartmentItemsIsNotEmpty();
        masterDepartmentItems.forEach(masterDepartmentItem -> {
            Integer maximumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMaximumQuantity();
            Integer minimumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMinimumQuantity();
            Integer totalQuantity = masterDepartmentItem.getDepartmentItems().stream()
                    .peek(item -> {
                        if(item.getQuantity() == null)
                            item.setQuantity(0);

                    })
                    .mapToInt(ProcessingLabEntity::getQuantity).sum();
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

            MasterProcessingLabOrderDetailEntity newOrderDetail = MasterProcessingLabOrderDetailEntity.builder()
                    .totalPrice(Precision.round(totalPrice, 2))
                    .totalQuantity(totalQuantity)
                    .orderQuantity(orderQuantity)
                    .masterDepartmentItem(masterDepartmentItem)
                    .build();
            masterDepartmentItem.setOrderDetail(newOrderDetail);
            repository.save(masterDepartmentItem);
            if(masterDepartmentItem.getOrderDetail() == null) {

            }
        });
        return "SUCCESS";
    }
}
