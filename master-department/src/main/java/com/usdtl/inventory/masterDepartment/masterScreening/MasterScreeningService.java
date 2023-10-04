package com.usdtl.inventory.masterDepartment.masterScreening;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.masterExtractions.MasterExtractionsOrderDetailEntity;
import com.usdtl.inventory.masterDepartment.masterRd.MasterRdEntity;
import com.usdtl.inventory.masterDepartment.masterRd.MasterRdOrderDetailEntity;
import com.usdtl.inventory.masterDepartment.masterRd.RdEntity;
import com.usdtl.inventory.masterDepartment.masterShipping.MasterShippingOrderDetailEntity;
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
public class MasterScreeningService {
    private MasterScreeningRepository repository;
    private MasterScreeningOrderDetailRepository orderDetailRepository;

    public MasterScreeningEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterScreeningEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public Page<MasterScreeningEntity> filterItems(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<MasterScreeningEntity> sorItems(Integer page, String column, String direction) {
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

    public MasterScreeningEntity create(MasterScreeningEntity request, Department department) {
        MasterScreeningEntity master = MasterScreeningEntity.builder()
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

    private MasterScreeningEntity getMasterDepartmentEntity(Department department, MasterScreeningEntity master) {
        if(department == Department.SCREENING) {
            ScreeningEntity item = new ScreeningEntity();
            List<ScreeningEntity> items = new ArrayList<>();
            items.add(item);
            master.setDepartmentItems(items);
        }

        repository.save(master);
        return master;
    }

    public MasterScreeningEntity assign(Integer id, Department department) {
        MasterScreeningEntity masterDepartment = repository.findById(id).orElseThrow(() -> new NotFoundException("Item associated with id: " + id + " not found"));
        return getMasterDepartmentEntity(department, masterDepartment);
    }

    public String syncOrderDetails() {
        List<MasterScreeningEntity> masterDepartmentItems = repository.findByDepartmentItemsIsNotEmpty();
        masterDepartmentItems.forEach(masterDepartmentItem -> {
            if(masterDepartmentItem.getOrderDetail() == null) {
                Integer maximumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMaximumQuantity();
                Integer minimumQuantity = masterDepartmentItem.getDepartmentItems().get(0).getMinimumQuantity();
                Integer totalQuantity = masterDepartmentItem.getDepartmentItems().stream()
                        .peek(item -> {
                            if(item.getQuantity() == null)
                                item.setQuantity(0);

                        })
                        .mapToInt(ScreeningEntity::getQuantity).sum();
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

                MasterScreeningOrderDetailEntity newOrderDetail = MasterScreeningOrderDetailEntity.builder()
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
