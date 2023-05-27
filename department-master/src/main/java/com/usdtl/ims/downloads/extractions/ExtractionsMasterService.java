package com.usdtl.ims.downloads.extractions;

import com.usdtl.ims.clients.DepartmentMasterResponse;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.MasterDepartmentResponse;
import com.usdtl.ims.clients.DepartmentResponse;
import com.usdtl.ims.clients.request.ExtractionsMasterRequest;
import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExtractionsMasterService {
    private ExtractionsMasterRepository repository;
    private MasterDepartmentClient client;

    public ExtractionsMasterEntity createItem(ExtractionsMasterRequest request) {
        ExtractionsMasterEntity newItem = ExtractionsMasterEntity.builder()
                .location(request.location())
                .quantity(request.quantity())
                .min_quantity(request.min_quantity())
                .max_quantity(request.max_quantity())
                .usage_level(request.usage_level())
                .lot_number(request.lot_number())
                .expiration_date(request.expiration_date())
                .received_date(request.received_date())
                .build();

        repository.save(newItem);
        return newItem;
    };



    public Page<ExtractionsMasterEntity> getExtractionsExperienceItemsByPage(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, 10);
        return repository.findAll(pageRequest);
    }

    public void deleteItemById(Integer id) {
        boolean exists = repository.existsById(id);
        if(!exists) {
            throw new NotFoundException("Item associated with id: " + id + " not found");
        }
        repository.deleteById(id);

    }

    public Page<DepartmentMasterResponse> getExperienceItemsByPage(Integer page) {
        List<DepartmentMasterResponse> departmentExtractionsItems = new ArrayList<>();

        PageRequest pageRequest = PageRequest.of(page, 10);
        Long extractionsItemsCount = repository.count();
        List<ExtractionsMasterEntity> extractionsItems = repository.findAll(pageRequest).getContent();
        extractionsItems.forEach((extractionsItem) -> {
            DepartmentMasterResponse experienceResponse = DepartmentMasterResponse.builder()
                    .department_id(extractionsItem.getId())
                    .item_id(extractionsItem.getMasterItem().getId())
                    .item(extractionsItem.getMasterItem().getItem())
                    .purchase_unit(extractionsItem.getMasterItem().getPurchase_unit())
                    .part_number(extractionsItem.getMasterItem().getPart_number())
                    .recent_cn(extractionsItem.getMasterItem().getRecent_cn())
                    .recent_vendor(extractionsItem.getMasterItem().getRecent_vendor())
                    .location(extractionsItem.getLocation())
                    .total_quantity(getTotalQuantity(extractionsItem.getMasterItem().getId()))
                    .usage_level(extractionsItem.getUsage_level())
                    .min_quantity(extractionsItem.getMin_quantity())
                    .max_quantity(extractionsItem.getMax_quantity())
                    .order_quantity(getOrderQuantity(extractionsItem.getMax_quantity(), extractionsItem.getMin_quantity(), extractionsItem.getQuantity()))
                    .unit_price(extractionsItem.getMasterItem().getAverage_unit_price())
                    .total_price(extractionsItem.getMasterItem().getAverage_unit_price() * getTotalQuantity(extractionsItem.getMasterItem().getId()))
                    .lot_number(extractionsItem.getLot_number())
                    .category(extractionsItem.getMasterItem().getCategory())
                    .comment(extractionsItem.getMasterItem().getComment())
                    .build();
            departmentExtractionsItems.add(experienceResponse);
        });

        return new PageImpl<>(departmentExtractionsItems, pageRequest, extractionsItemsCount);
    }

    public ExtractionsMasterEntity getItemById(Integer id) throws NotFoundException {
        ExtractionsMasterEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
        return item;
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

    private Integer getTotalQuantity(Integer id) {
        MasterDepartmentResponse response = client.getMasterExtractionsItemById(id);
        return response.departmentItems().stream().mapToInt(DepartmentResponse::quantity).sum();
    }

    public List<DepartmentMasterResponse> getScheduledEmailItems() {
        List<DepartmentMasterResponse> transformedItems = new ArrayList<>();
        List<ExtractionsMasterEntity> items = (List<ExtractionsMasterEntity>) repository.findAll();

        items.forEach((item) -> {
            Integer order_quantity = getOrderQuantity(item.getMax_quantity(), item.getMin_quantity(), item.getQuantity());
            if(order_quantity > 0) {
                DepartmentMasterResponse response = DepartmentMasterResponse.builder()
                        .department_id(item.getId())
                        .item_id(item.getMasterItem().getId())
                        .item(item.getMasterItem().getItem())
                        .purchase_unit(item.getMasterItem().getPurchase_unit())
                        .part_number(item.getMasterItem().getPart_number())
                        .recent_cn(item.getMasterItem().getRecent_cn())
                        .recent_vendor(item.getMasterItem().getRecent_vendor())
                        .location(item.getLocation())
                        .total_quantity(getTotalQuantity(item.getMasterItem().getId()))
                        .usage_level(item.getUsage_level())
                        .min_quantity(item.getMin_quantity())
                        .max_quantity(item.getMax_quantity())
                        .order_quantity(order_quantity)
                        .unit_price(item.getMasterItem().getAverage_unit_price())
                        .total_price(item.getMasterItem().getAverage_unit_price() * getTotalQuantity(item.getMasterItem().getId()))
                        .lot_number(item.getLot_number())
                        .category(item.getMasterItem().getCategory())
                        .comment(item.getMasterItem().getComment())
                        .build();
                transformedItems.add(response);
            }
        });
        return transformedItems;
    }
}