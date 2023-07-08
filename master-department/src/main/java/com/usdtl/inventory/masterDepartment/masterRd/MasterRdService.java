package com.usdtl.inventory.masterDepartment.masterRd;

import com.usdtl.ims.clients.response.DepartmentResponse;
import com.usdtl.ims.clients.response.MasterDepartmentResponse;
import com.usdtl.ims.common.exceptions.common.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class MasterRdService {
    private MasterRdRepository repository;

    public MasterRdEntity getItemById(Integer id) throws NotFoundException {
        MasterRdEntity masterItem = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return masterItem;
    }

    public Page<MasterRdEntity> getMasterDepartmentItems(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findByDepartmentItemsIsNotEmpty(pageRequest);
    }

    public Page<MasterDepartmentResponse> getMasterDepartmentItemsTransformed(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        List<MasterDepartmentResponse> masterDepartmentItemResponse = new ArrayList<>();

        List<MasterRdEntity> masterDepartmentItems = (List<MasterRdEntity>) repository.findAll();

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
                        .part_number(masterDepartmentItem.getPart_number())
                        .recent_cn(masterDepartmentItem.getRecent_cn())
                        .recent_vendor(masterDepartmentItem.getRecent_vendor())
                        .fisher_cn(masterDepartmentItem.getFisher_cn())
                        .vwr_cn(masterDepartmentItem.getVwr_cn())
                        .lab_source_cn(masterDepartmentItem.getLab_source_cn())
                        .other_cn(masterDepartmentItem.getOther_cn())
                        .purchase_unit(masterDepartmentItem.getPurchase_unit())
                        .average_unit_price(masterDepartmentItem.getUnit_price())
                        .category(masterDepartmentItem.getCategory())
                        .comment(masterDepartmentItem.getComment())
                        .type(masterDepartmentItem.getType())
                        .group(masterDepartmentItem.getGroup())
                        .drug_class(masterDepartmentItem.getDrug_class())
                        .departmentItems(departmentItems)
                        .total_price(getTotalQuantity(departmentItems) * masterDepartmentItem.getUnit_price())
                        .total_quantity(getTotalQuantity(departmentItems))
                        .build();
                masterDepartmentItemResponse.add(masterDepartmentResponseItem);
            }
        });

        List<MasterDepartmentResponse> pagedItems = masterDepartmentItemResponse.subList(page, page + 10);

        return new PageImpl<>(pagedItems, pageRequest, masterDepartmentItemResponse.size());
    }

    private Integer getTotalQuantity(List<DepartmentResponse> departmentItems) {
        return departmentItems.stream().mapToInt(DepartmentResponse::quantity).sum();
    }
}
