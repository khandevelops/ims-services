package com.usdtl.inventory.masterDepartment.masterExtractions;

import com.usdtl.ims.clients.response.DepartmentResponse;
import com.usdtl.ims.clients.response.MasterDepartmentResponse;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import com.usdtl.ims.common.exceptions.constants.Department;
import com.usdtl.inventory.masterDepartment.common.entities.ExtractionsEntity;
import com.usdtl.inventory.masterDepartment.common.repositories.ExtractionsRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MasterExtractionsService {
    private MasterExtractionsRepository masterExtractionsRepository;
    private ExtractionsRepository extractionsRepository;

    public MasterExtractionsEntity getItemById(Integer id) throws NotFoundException {
        MasterExtractionsEntity masterItem = masterExtractionsRepository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }

    public Page<MasterExtractionsEntity> getMasterDepartmentItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return masterExtractionsRepository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public Page<MasterExtractionsEntity> getMasterDepartmentItems(String keyword, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return masterExtractionsRepository.findAllByKeyword(keyword, pageRequest);
    }

    public Page<MasterDepartmentResponse> getMasterDepartmentItemsTransformed(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<MasterDepartmentResponse> masterDepartmentItemResponse = new ArrayList<>();

        List<MasterExtractionsEntity> masterDepartmentItems = (List<MasterExtractionsEntity>) masterExtractionsRepository.findAll();

        masterDepartmentItems.forEach(masterDepartmentItem -> {
            if(!masterDepartmentItem.getDepartmentItems().isEmpty()) {
                List<DepartmentResponse> departmentItems = new ArrayList<>();
                masterDepartmentItem.getDepartmentItems().forEach(departmentItem -> {
                    DepartmentResponse departmentItemResponse = DepartmentResponse.builder()
                            .id(departmentItem.getId())
                            .location(departmentItem.getLocation())
                            .quantity(departmentItem.getQuantity())
                            .lot_number(departmentItem.getLot_number())
                            .expiration_date((departmentItem.getExpiration_date()))
                            .received_date((departmentItem.getReceived_date()))
                            .build();
                    departmentItems.add(departmentItemResponse);
                });
                MasterDepartmentResponse masterDepartmentResponseItem = MasterDepartmentResponse.builder()
                        .id(masterDepartmentItem.getId())
                        .item(masterDepartmentItem.getItem())
                        .manufacturer(masterDepartmentItem.getManufacturer())
                        .partNumber(masterDepartmentItem.getPartNumber())
                        .recentCN(masterDepartmentItem.getRecentCN())
                        .recentVendor(masterDepartmentItem.getRecentVendor())
                        .fisherCN(masterDepartmentItem.getFisherCN())
                        .vwrCN(masterDepartmentItem.getVwrCN())
                        .labSourceCN(masterDepartmentItem.getLabSourceCN())
                        .otherCN(masterDepartmentItem.getOtherCN())
                        .purchaseUnit(masterDepartmentItem.getPurchaseUnit())
                        .unitPrice(masterDepartmentItem.getUnitPrice())
                        .category(masterDepartmentItem.getCategory())
                        .comment(masterDepartmentItem.getComment())
                        .itemType(masterDepartmentItem.getItemType())
                        .itemGroup(masterDepartmentItem.getItemGroup())
                        .druClass(masterDepartmentItem.getDrugClass())
                        .departmentItems(departmentItems)
                        .build();
                masterDepartmentItemResponse.add(masterDepartmentResponseItem);
            }
        });

        List<MasterDepartmentResponse> pagedItems = masterDepartmentItemResponse.subList(page, page + 10);

        return new PageImpl<>(pagedItems, pageRequest, masterDepartmentItemResponse.size());
    }

    private Integer getOrderQuantity(Integer max_quantity, Integer min_quantity, Integer quantity) {
        if(max_quantity != null && min_quantity != null) {
            if(min_quantity == 1 && max_quantity == 1) {
                if(quantity < 1) {
                    return 1;
                }
            } else if(quantity <= min_quantity) {
                if(max_quantity - min_quantity < 0) {
                    throw new RuntimeException();
                }
                return max_quantity - min_quantity;
            }
        }
        return 0;
    }

    private Integer getTotalQuantity(List<DepartmentResponse> departmentItems) {
        return departmentItems.stream().mapToInt(DepartmentResponse::quantity).sum();
    }
}
