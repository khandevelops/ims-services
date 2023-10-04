package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
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
public class MasterExtractionsService {
    private MasterExtractionsRepository repository;

    public MasterExtractionsEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterExtractionsEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public Page<MasterExtractionsEntity> filterItems(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<MasterExtractionsEntity> sortItems(Integer page, String column, String direction) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        Sort sort = null;
        if(direction.isEmpty()) {
            sort = Sort.by("id").ascending();
            pageRequest = PageRequest.of(page, 10, sort);
        }
        if(direction.equals("ASC")) {
            if(column.equals("totalQuantity")) {
                sort = Sort.by("orderDetail.totalQuantity").ascending();
            } else if (column.equals("orderQuantity")) {
                sort = Sort.by("orderDetail.orderQuantity").ascending();
            } else if (column.equals("totalPrice")) {
                sort = Sort.by("orderDetail.totalPrice").ascending();
            } else {
                sort = Sort.by(column).ascending();
            }
            pageRequest = PageRequest.of(page, 10, sort);
        }
        if(direction.equals("DESC")) {
            if(column.equals("totalQuantity")) {
                sort = Sort.by("orderDetail.totalQuantity").descending();
            } else if (column.equals("orderQuantity")) {
                sort = Sort.by("orderDetail.orderQuantity").descending();
            } else if (column.equals("totalPrice")) {
                sort = Sort.by("orderDetail.totalPrice").descending();
            } else {
                sort = Sort.by(column).descending();
            }
            pageRequest = PageRequest.of(page, 10, sort);
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


        return getMasterExtractionsEntity(master);
    }

    private MasterExtractionsEntity getMasterExtractionsEntity(MasterExtractionsEntity master) {
        ExtractionsEntity item = new ExtractionsEntity();
        List<ExtractionsEntity> items = new ArrayList<>();
        items.add(item);
        master.setDepartmentItems(items);

        repository.save(master);
        return master;
    }

    public MasterExtractionsEntity assign(Integer id) {
        MasterExtractionsEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterExtractionsEntity(masterDepartment);
    }

    public String syncOrderDetails() {
        List<MasterExtractionsEntity> masterDepartmentItems = repository.findByDepartmentItemsIsNotEmpty();
        masterDepartmentItems.forEach(masterDepartmentItem -> {
            if(masterDepartmentItem.getOrderDetail() == null) {
                Integer maximumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMaximumQuantity();
                Integer minimumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMinimumQuantity();
                Integer totalQuantity = masterDepartmentItem.getDepartmentItems().stream()
                        .peek(item -> {
                            if(item.getQuantity() == null)
                                item.setQuantity(0);

                        })
                        .mapToInt(ExtractionsEntity::getQuantity).sum();
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

                MasterExtractionsOrderDetailEntity newOrderDetail = com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsOrderDetailEntity.builder()
                        .totalPrice(Precision.round(totalPrice, 2))
                        .totalQuantity(totalQuantity)
                        .orderQuantity(orderQuantity)
                        .masterDepartmentEntity(masterDepartmentItem)
                        .build();
                masterDepartmentItem.setOrderDetail(newOrderDetail);
                repository.save(masterDepartmentItem);
            }
        });
        return "SUCCESS";
    }
}
