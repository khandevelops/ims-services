package com.usdtl.ims.departmentMaster.extractionsMaster;

import com.usdtl.ims.clients.MasterDepartmentClient;
import com.usdtl.ims.clients.MasterDepartmentResponse;
import com.usdtl.ims.clients.DepartmentResponse;
import com.usdtl.ims.clients.responseRecord.DepartmentMasterDownloadExcelResponse;
import com.usdtl.ims.common.exceptions.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ExtractionsMasterDownloadService {
    private ExtractionsMasterDownloadRepository repository;
    private MasterDepartmentClient client;
    public List<DepartmentMasterDownloadExcelResponse> getDepartmentMasterExcelItems() {
        List<DepartmentMasterDownloadExcelResponse> departmentMasterExcelItems = new ArrayList<>();

        List<ExtractionsMasterDownloadEntity> extractionsMasterItems = (List<ExtractionsMasterDownloadEntity>) repository.findAll();
        extractionsMasterItems.forEach((extractionsItem) -> {
            DepartmentMasterDownloadExcelResponse experienceResponse = DepartmentMasterDownloadExcelResponse.builder()
                    .department_item_id(extractionsItem.getId())
                    .master_item_id(extractionsItem.getMasterItem().getId())
                    .item(extractionsItem.getMasterItem().getItem())
                    .purchase_unit(extractionsItem.getMasterItem().getPurchase_unit())
                    .part_number(extractionsItem.getMasterItem().getPart_number())
                    .recent_cn(extractionsItem.getMasterItem().getRecent_cn())
                    .total_quantity(getTotalQuantity(extractionsItem.getMasterItem().getId()))
                    .quantity(extractionsItem.getQuantity())
                    .location(extractionsItem.getLocation())
                    .usage_level(extractionsItem.getUsage_level())
                    .min_quantity(extractionsItem.getMin_quantity())
                    .max_quantity(extractionsItem.getMax_quantity())
                    .order_quantity(getOrderQuantity(extractionsItem.getMax_quantity(), extractionsItem.getMin_quantity(), extractionsItem.getQuantity()))
                    .unit_price(extractionsItem.getMasterItem().getAverage_unit_price())
                    .total_price(extractionsItem.getMasterItem().getAverage_unit_price() * getTotalQuantity(extractionsItem.getMasterItem().getId()))
                    .lot_number(extractionsItem.getLot_number())
                    .category(extractionsItem.getMasterItem().getCategory())
                    .comment(extractionsItem.getMasterItem().getComment())
                    .received_date(extractionsItem.getReceived_date())
                    .expiration_date(extractionsItem.getExpiration_date())
                    .build();
            departmentMasterExcelItems.add(experienceResponse);
        });

        return departmentMasterExcelItems;
    }

    public ExtractionsMasterDownloadEntity getItemById(Integer id) throws NotFoundException {
        ExtractionsMasterDownloadEntity item = repository.findById(id).orElseThrow(() ->  new NotFoundException("Item associated with id: " + id + " not found"));
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
        return response.extractionsItems().stream().mapToInt(DepartmentResponse::quantity).sum();
    }
}