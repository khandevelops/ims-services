package com.usdtl.inventory.masterDepartment.masterQcInternalStandards;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsOrderDetailEntity;
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
public class MasterQcInternalStandardsService {
    private MasterQcInternalStandardsRepository repository;
    private MasterQcInternalStandardsOrderDetailRepository orderDetailRepository;

    public MasterQcInternalStandardsEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterQcInternalStandardsEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public Page<MasterQcInternalStandardsEntity> filterItems(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<MasterQcInternalStandardsEntity> sorItems(Integer page, String column, String direction) {
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

    public Page<MasterQcInternalStandardsEntity> getMasterDepartmentItems(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
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


        return getMasterDepartmentEntity(department, master);
    }

    private MasterQcInternalStandardsEntity getMasterDepartmentEntity(Department department, MasterQcInternalStandardsEntity master) {
        QcInternalStandardsEntity item = new QcInternalStandardsEntity();
        List<QcInternalStandardsEntity> items = new ArrayList<>();
        items.add(item);
        master.setDepartmentItems(items);

        repository.save(master);
        return master;
    }

    public MasterQcInternalStandardsEntity assign(Integer id, Department department) {
        MasterQcInternalStandardsEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterDepartmentEntity(department, masterDepartment);
    }

    public String syncOrderDetails() {
        List<MasterQcInternalStandardsEntity> masterDepartmentItems = repository.findByDepartmentItemsIsNotEmpty();
        masterDepartmentItems.forEach(masterDepartmentItem -> {
            if(masterDepartmentItem.getOrderDetail() == null) {
                Integer maximumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMaximumQuantity();
                Integer minimumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMinimumQuantity();
                Integer totalQuantity = masterDepartmentItem.getDepartmentItems().stream()
                        .peek(item -> {
                            if(item.getQuantity() == null)
                                item.setQuantity(0);

                        })
                        .mapToInt(QcInternalStandardsEntity::getQuantity).sum();
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

                MasterQcInternalStandardsOrderDetailEntity newOrderDetail = MasterQcInternalStandardsOrderDetailEntity.builder()
                        .totalPrice(Precision.round(totalPrice, 2))
                        .totalQuantity(totalQuantity)
                        .orderQuantity(orderQuantity)
                        .masterDepartmentItem(masterDepartmentItem)
                        .build();
                masterDepartmentItem.setOrderDetail(newOrderDetail);
                repository.save(masterDepartmentItem);
            }

        });
        return "SUCCESS";
    }
}
