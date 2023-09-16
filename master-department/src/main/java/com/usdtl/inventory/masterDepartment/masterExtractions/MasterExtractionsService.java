package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
//import com.usdtl.inventory.masterDepartment.masterTotal.MasterTotalRepository;
//import com.usdtl.inventory.masterDepartment.orderDetail.OrderDetailEntity;
//import com.usdtl.inventory.masterDepartment.orderDetail.OrderDetailRepository;
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
    private MasterExtractionsOrderDetailRepository orderDetailRepository;

    public MasterExtractionsEntity getItem(Integer id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
    }

    public Page<MasterExtractionsEntity> getItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

//    public void getMasterTotal(Page<MasterExtractionsEntity> masterDepartmentItems) {
//        List<OrderDetailEntity> orderDetailEntityList = new ArrayList<>();
//        masterDepartmentItems.forEach(masterDepartmentItem -> {
//            Integer totalQuantity = masterDepartmentItem.getDepartmentItems().stream().mapToInt(ExtractionsEntity::getQuantity).sum();
//            OrderDetailEntity masterTotal = OrderDetailEntity.builder()
//                    .totalQuantity(totalQuantity)
//                    .totalPrice(masterDepartmentItem.getUnitPrice() * totalQuantity)
//                    .build();
//            orderDetailEntityList.add(masterTotal);
//        });
//        orderDetailRepository.saveAll(orderDetailEntityList);
//    }

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
//        if(direction.equals("ASC")) {
//            if(column.equals("totalQuantity")) {
//                return repository.findAllByTotalQuantityOrderByAsc(pageRequest);
//            } else if (column.equals("totalPrice")) {
//                return repository.findAllByTotalPriceOrderByAsc(pageRequest);
//            } else {
//                Sort sort = Sort.by(column).ascending();
//                pageRequest = PageRequest.of(page, 10, sort);
//            }
//        }
//        if(direction.equals("DESC")) {
//            if(column.equals("totalQuantity")) {
//                return repository.findAllByTotalQuantityOrderByDesc(pageRequest);
//            } else if (column.equals("totalPrice")) {
//                return repository.findAllByTotalPriceOrderByDesc(pageRequest);
//            } else {
//                Sort sort = Sort.by(column).descending();
//                pageRequest = PageRequest.of(page, 10, sort);
//            }
//        }
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

    public String syncOrderDetails() {
        List<MasterExtractionsEntity> masterExtractionsItems = repository.findByDepartmentItemsIsNotEmpty();
        masterExtractionsItems.forEach(masterExtractionsItem -> {
            Integer maximumQuantity = masterExtractionsItem.getDepartmentItems().get(0).getMaximumQuantity();
            Integer minimumQuantity = masterExtractionsItem.getDepartmentItems().get(0).getMinimumQuantity();
            Integer totalQuantity = masterExtractionsItem.getDepartmentItems().stream().mapToInt(ExtractionsEntity::getQuantity).sum();
            Double totalPrice = masterExtractionsItem.getUnitPrice() * totalQuantity;
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

            MasterExtractionsOrderDetailEntity newOrderDetail = MasterExtractionsOrderDetailEntity.builder()
                    .totalPrice(totalPrice)
                    .totalQuantity(totalQuantity)
                    .orderQuantity(orderQuantity)
                    .masterExtractionsItem(masterExtractionsItem)
                    .build();
            orderDetailRepository.save(newOrderDetail);
        });
        return "SUCCESS";
    }
}
